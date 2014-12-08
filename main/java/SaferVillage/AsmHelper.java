package SaferVillage;

import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.tree.AbstractInsnNode.INSN;
import static org.objectweb.asm.tree.AbstractInsnNode.JUMP_INSN;
import static org.objectweb.asm.tree.AbstractInsnNode.LABEL;
import static org.objectweb.asm.tree.AbstractInsnNode.LINE;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.commons.RemappingClassAdapter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class AsmHelper
{
	public static boolean debug = false;

	public static byte[] loadclass(String zip_name, String classname)
	{
		ZipFile zf = null;
		InputStream zi = null;
		byte[] bytes = null;;

		try
		{
			zf = new ZipFile(zip_name);
			ZipEntry ze = zf.getEntry(classname.replace('\\', '/'));

			if (ze != null)
			{
				int len = (int) ze.getSize();
				zi = zf.getInputStream(ze);
				bytes = new byte[len];
				readZip(zi, bytes);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			bytes = null;
		}
		finally
		{
			if (zi != null)
			{
				try
				{
					zi.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			if (zf != null)
			{
				try
				{
					zf.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return bytes;
	}

	public static byte[] readZip(InputStream zi, byte[] bytes) throws IOException
	{
		int len = bytes.length;
		int MAX_READ = 1024;
		int readed = 0, readsize, ret;
		while (readed < len)
		{
			readsize = MAX_READ;
			if (len - readed < MAX_READ)
			{
				readsize = len - readed;
			}
			ret = zi.read(bytes, readed, readsize);
			if (ret == -1) break;
			readed += ret;
		}
		return bytes;
	}

	public static void exportClass(String name, byte[] buf)
	{
		BufferedOutputStream fis = null;
		try
		{
			File file = new File(name + ".class");
			fis = new BufferedOutputStream(new FileOutputStream(file));
			fis.write(buf);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (fis != null)
				{
					fis.close();
				}
			}
			catch (IOException e)
			{}
		}
		return;
	}

	public static MethodNode cloneMethod(byte[] bytes, String name, String desc)
	{
		name = name.replaceAll("\\.", "/");
		desc = desc.replaceAll("\\.", "/");
		ClassReader classReader = new ClassReader(bytes);
		ClassNode classNode = new ClassNode();
		classReader.accept(classNode, 0);
		Iterator < MethodNode > methods = classNode.methods.iterator();

		MethodNode CopyMethod = null;
		MethodNode addMethod = null;
		try
		{
			while (methods.hasNext())
			{
				MethodNode m = methods.next();
				//System.out.println("visit " + m.name + " " + m.desc);
				if (m.name == null || m.desc == null)
				{
					continue;
				}
				if (m.name.equals(name) && m.desc.equals(desc))
				{
					CopyMethod = m;
					break;
				}
			}
			if (CopyMethod != null)
			{
				String[] exceptions = (String[]) CopyMethod.exceptions.toArray(new String[0]);
				addMethod = new MethodNode(CopyMethod.access, name, desc, CopyMethod.signature, exceptions);
				InsnList addlist = new InsnList();
				addlist.clear();
				addlist.add(getPostReturnInsnList(CopyMethod));
				addMethod.instructions.add(addlist);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Template method " + name + desc + " not found.");
		}
		return addMethod;
	}

	public static MethodNode reallocMethod(MethodNode method, InsnList insns)
	{
		String[] exceptions = (String[]) method.exceptions.toArray(new String[0]);
		MethodNode addMethod = new MethodNode(method.access, method.name, method.desc, method.signature, exceptions);
		addMethod.instructions = insns;
		return addMethod;
	}

	public static AbstractInsnNode getPreReturnInsn(MethodNode method)
	{
		int chk = 0;
		AbstractInsnNode insn = method.instructions.getLast();
		AbstractInsnNode ret = null;
		while (insn != null)
		{
			//System.out.println(insn + ":" + opcode2str.get(insn.getOpcode()));
			if (chk == 0 && insn.getType() == INSN && (insn.getOpcode() >= IRETURN && insn.getOpcode() <= RETURN))
			{
				chk = 1;
			}
			else
			if (chk == 1 && insn.getType() == LINE)
			{
				ret = insn;
				chk = 2;
			}
			else
			if (chk == 2 && insn.getType() == LABEL)
			{
				// Check: if before RETURN LABEL used in before lines
				AbstractInsnNode tmp = method.instructions.getFirst();
				while (tmp != null)
				{
					if (tmp.getType() == JUMP_INSN && ((JumpInsnNode) tmp).label == insn) break;
					if (tmp == insn) break;
					tmp = tmp.getNext();
				}
				if (tmp != null && tmp != insn)
				{
					return ret;
				}
				return insn;
			}
			insn = insn.getPrevious();
		}
		return ret;
	}

	public static AbstractInsnNode getPostReturnInsn(MethodNode method)
	{
		int chk = 0;
		AbstractInsnNode insn = method.instructions.getFirst();

		while (insn != null)
		{
			if (chk == 0 && insn.getType() == INSN && (insn.getOpcode() >= IRETURN && insn.getOpcode() <= RETURN))
			{
				chk = 1;
			}
			else
			if (chk == 1)
			{
				return insn;
			}
			insn = insn.getNext();
		}
		return null;
	}

	public static InsnList getPreReturnInsnList(MethodNode method)
	{
		InsnList newlist = new InsnList();

		AbstractInsnNode insn = method.instructions.getFirst();
		AbstractInsnNode retr = getPreReturnInsn(method);
		while (insn != null)
		{
			if (insn == retr) break;
			if (debug) System.out.println(insn + ":" + opcode2str.get(insn.getOpcode()));
			newlist.add(insn);
			insn = insn.getNext();
		}
		return newlist;
	}

	public static InsnList getPostReturnInsnList(MethodNode method)
	{
		InsnList newlist = new InsnList();

		AbstractInsnNode insn = method.instructions.getFirst();
		AbstractInsnNode retr = getPostReturnInsn(method);
		while (insn != null)
		{
			if (insn == retr) break;
			if (debug) System.out.println(insn + ":" + opcode2str.get(insn.getOpcode()));
			newlist.add(insn);
			insn = insn.getNext();
		}
		return newlist;
	}

	public static boolean isMethodEqual(AbstractInsnNode insn, int opcode, String owner, String name, String desc)
	{
		owner = owner.replaceAll("\\.", "/");
		name = name.replaceAll("\\.", "/");
		desc = desc.replaceAll("\\.", "/");
		return insn.getOpcode() == opcode && ((MethodInsnNode) insn).owner.equals(owner) && ((MethodInsnNode) insn).name.equals(name) && ((MethodInsnNode) insn).desc.equals(desc);
	}

	public static boolean isFieldEqual(AbstractInsnNode insn, int opcode, String owner, String name, String desc)
	{
		owner = owner.replaceAll("\\.", "/");
		name = name.replaceAll("\\.", "/");
		desc = desc.replaceAll("\\.", "/");
		return insn.getOpcode() == opcode && ((FieldInsnNode) insn).owner.equals(owner) && ((FieldInsnNode) insn).name.equals(name) && ((FieldInsnNode) insn).desc.equals(desc);
	}

	public static byte[] remapClass(byte[] bytes, final String oldname, final String newname)
	{
		if (bytes == null) return null;

		Remapper remapper = new Remapper()
		{
			@Override
			public String map(String s)
			{
				s = s.replaceAll(oldname, newname);
				return s;
			}
		};

		byte[] ret = null;
		try
		{
			ClassReader classReader = new ClassReader(bytes);
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			ClassNode classNode = new ClassNode();
			classReader.accept(new RemappingClassAdapter(classNode, remapper), ClassReader.EXPAND_FRAMES);
			//classNode.name = newclassname;
			classNode.accept(classWriter);
			ret = classWriter.toByteArray();
		}
		catch (Exception e){}
		return ret;
	}

	public static HashMap < Integer, String > access2str = new HashMap < Integer, String > () {{
		put(1, "ACC_PUBLIC");
		put(2, "ACC_PRIVATE");
		put(4, "ACC_PROTECTED");
		put(8, "ACC_STATIC");
		put(16, "ACC_FINAL");
		put(32, "ACC_SUPER");
		put(64, "ACC_VOLATILE");
		put(128, "ACC_VARARGS");
		put(256, "ACC_NATIVE");
		put(512, "ACC_INTERFACE");
		put(1024, "ACC_ABSTRACT");
		put(2048, "ACC_STRICT");
		put(4096, "ACC_SYNTHETIC");
		put(8192, "ACC_ANNOTATION");
		put(16384, "ACC_ENUM");
		put(32768, "ACC_MANDATED");
		put(131072, "ACC_DEPRECATED");
	}};

	public static HashMap < Integer, String > insntype2str = new HashMap < Integer, String > () {{
		put(0, "INSN");
		put(1, "INT_INSN");
		put(2, "VAR_INSN");
		put(3, "TYPE_INSN");
		put(4, "FIELD_INSN");
		put(5, "METHOD_INSN");
		put(6, "INVOKE_DYNAMIC_INSN");
		put(7, "JUMP_INSN");
		put(8, "LABEL");
		put(9, "LDC_INSN");
		put(10, "IINC_INSN");
		put(11, "TABLESWITCH_INSN");
		put(12, "LOOKUPSWITCH_INSN");
		put(13, "MULTIANEWARRAY_INSN");
		put(14, "FRAME");
		put(15, "LINE");
	}};
	public static HashMap < Integer, String > array2str = new HashMap < Integer, String > () {{
		put(4, "T_BOOLEAN");
		put(5, "T_CHAR");
		put(6, "T_FLOAT");
		put(7, "T_DOUBLE");
		put(8, "T_BYTE");
		put(9, "T_SHORT");
		put(10, "T_INT");
		put(11, "T_LONG");
	}};
	public static HashMap < Integer, String > handle2str = new HashMap < Integer, String > () {{
		put(1, "H_GETFIELD");
		put(2, "H_GETSTATIC");
		put(3, "H_PUTFIELD");
		put(4, "H_PUTSTATIC");
		put(5, "H_INVOKEVIRTUAL");
		put(6, "H_INVOKESTATIC");
		put(7, "H_INVOKESPECIAL");
		put(8, "H_NEWINVOKESPECIAL");
		put(9, "H_INVOKEINTERFACE");
	}};

	public static HashMap < Integer, String > frame2str = new HashMap < Integer, String > () {{
		put(-1, "F_NEW");
		put(0, "F_FULL");
		put(1, "F_APPEND");
		put(2, "F_CHOP");
		put(3, "F_SAME");
		put(4, "F_SAME1");
	}};

	public static HashMap < Integer, String > opcode2str = new HashMap < Integer, String > () {{
		put(0, "NOP");
		put(1, "ACONST_NULL");
		put(2, "ICONST_M1");
		put(3, "ICONST_0");
		put(4, "ICONST_1");
		put(5, "ICONST_2");
		put(6, "ICONST_3");
		put(7, "ICONST_4");
		put(8, "ICONST_5");
		put(9, "LCONST_0");
		put(10, "LCONST_1");
		put(11, "FCONST_0");
		put(12, "FCONST_1");
		put(13, "FCONST_2");
		put(14, "DCONST_0");
		put(15, "DCONST_1");
		put(16, "BIPUSH");
		put(17, "SIPUSH");
		put(18, "LDC");
		put(21, "ILOAD");
		put(22, "LLOAD");
		put(23, "FLOAD");
		put(24, "DLOAD");
		put(25, "ALOAD");
		put(46, "IALOAD");
		put(47, "LALOAD");
		put(48, "FALOAD");
		put(49, "DALOAD");
		put(50, "AALOAD");
		put(51, "BALOAD");
		put(52, "CALOAD");
		put(53, "SALOAD");
		put(54, "ISTORE");
		put(55, "LSTORE");
		put(56, "FSTORE");
		put(57, "DSTORE");
		put(58, "ASTORE");
		put(79, "IASTORE");
		put(80, "LASTORE");
		put(81, "FASTORE");
		put(82, "DASTORE");
		put(83, "AASTORE");
		put(84, "BASTORE");
		put(85, "CASTORE");
		put(86, "SASTORE");
		put(87, "POP");
		put(88, "POP2");
		put(89, "DUP");
		put(90, "DUP_X1");
		put(91, "DUP_X2");
		put(92, "DUP2");
		put(93, "DUP2_X1");
		put(94, "DUP2_X2");
		put(95, "SWAP");
		put(96, "IADD");
		put(97, "LADD");
		put(98, "FADD");
		put(99, "DADD");
		put(100, "ISUB");
		put(101, "LSUB");
		put(102, "FSUB");
		put(103, "DSUB");
		put(104, "IMUL");
		put(105, "LMUL");
		put(106, "FMUL");
		put(107, "DMUL");
		put(108, "IDIV");
		put(109, "LDIV");
		put(110, "FDIV");
		put(111, "DDIV");
		put(112, "IREM");
		put(113, "LREM");
		put(114, "FREM");
		put(115, "DREM");
		put(116, "INEG");
		put(117, "LNEG");
		put(118, "FNEG");
		put(119, "DNEG");
		put(120, "ISHL");
		put(121, "LSHL");
		put(122, "ISHR");
		put(123, "LSHR");
		put(124, "IUSHR");
		put(125, "LUSHR");
		put(126, "IAND");
		put(127, "LAND");
		put(128, "IOR");
		put(129, "LOR");
		put(130, "IXOR");
		put(131, "LXOR");
		put(132, "IINC");
		put(133, "I2L");
		put(134, "I2F");
		put(135, "I2D");
		put(136, "L2I");
		put(137, "L2F");
		put(138, "L2D");
		put(139, "F2I");
		put(140, "F2L");
		put(141, "F2D");
		put(142, "D2I");
		put(143, "D2L");
		put(144, "D2F");
		put(145, "I2B");
		put(146, "I2C");
		put(147, "I2S");
		put(148, "LCMP");
		put(149, "FCMPL");
		put(150, "FCMPG");
		put(151, "DCMPL");
		put(152, "DCMPG");
		put(153, "IFEQ");
		put(154, "IFNE");
		put(155, "IFLT");
		put(156, "IFGE");
		put(157, "IFGT");
		put(158, "IFLE");
		put(159, "IF_ICMPEQ");
		put(160, "IF_ICMPNE");
		put(161, "IF_ICMPLT");
		put(162, "IF_ICMPGE");
		put(163, "IF_ICMPGT");
		put(164, "IF_ICMPLE");
		put(165, "IF_ACMPEQ");
		put(166, "IF_ACMPNE");
		put(167, "GOTO");
		put(168, "JSR");
		put(169, "RET");
		put(170, "TABLESWITCH");
		put(171, "LOOKUPSWITCH");
		put(172, "IRETURN");
		put(173, "LRETURN");
		put(174, "FRETURN");
		put(175, "DRETURN");
		put(176, "ARETURN");
		put(177, "RETURN");
		put(178, "GETSTATIC");
		put(179, "PUTSTATIC");
		put(180, "GETFIELD");
		put(181, "PUTFIELD");
		put(182, "INVOKEVIRTUAL");
		put(183, "INVOKESPECIAL");
		put(184, "INVOKESTATIC");
		put(185, "INVOKEINTERFACE");
		put(186, "INVOKEDYNAMIC");
		put(187, "NEW");
		put(188, "NEWARRAY");
		put(189, "ANEWARRAY");
		put(190, "ARRAYLENGTH");
		put(191, "ATHROW");
		put(192, "CHECKCAST");
		put(193, "INSTANCEOF");
		put(194, "MONITORENTER");
		put(195, "MONITOREXIT");
		put(197, "MULTIANEWARRAY");
		put(198, "IFNULL");
		put(199, "IFNONNULL");
	}};
}
