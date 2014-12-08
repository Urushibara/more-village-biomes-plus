package mvb;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.tree.AbstractInsnNode.*;
import maya.ASMHelper;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
 
public class MVBAsmTransformer implements IClassTransformer
{
	
    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {   
		//System.out.println("view : [" + name + "][" + transformedName + "]");
    	String EntryName;
    	if (
    	(transformedName.equals("net.minecraft.world.gen.structure.StructureVillagePieces$Path"))
    	)
        {
    		//System.out.println("[MVB+]: Class Loading : [" + name + "][" + transformedName + "]");
        	try {
                return modifyClass_Path(name, transformedName, bytes);
            }
            catch (Exception e){
                throw new RuntimeException("[MVB+]: failed : Class loading", e);
            }
        }
    	if(transformedName.equals("net.minecraft.world.gen.structure.StructureVillagePieces$Village")){

    		//System.out.println("[MVB+]: Class Loading : [" + name + "][" + transformedName + "]");
        	try {
                return modifyClass_village(name, transformedName, bytes);
            }
            catch (Exception e){
                throw new RuntimeException("[MVB+]: failed : Class loading", e);
            }
    	}
    	if(transformedName.equals("net.minecraft.world.gen.structure.StructureVillagePieces$Start")){

    		//System.out.println("[MVB+]: Class Loading : [" + name + "][" + transformedName + "]");
        	try {
                return modifyClass_Start(name, transformedName, bytes);
            }
            catch (Exception e){
                throw new RuntimeException("[MVB+]: failed : Class loading", e);
            }
    	}
    	if(transformedName.equals("net.minecraft.world.gen.structure.StructureVillagePieces$Well")){

    		//System.out.println("[MVB+]: Class Loading : [" + name + "][" + transformedName + "]");
        	try {
                return modifyClass_Well(name, transformedName, bytes);
            }
            catch (Exception e){
                throw new RuntimeException("[MVB+]: failed : Class loading", e);
            }
    	}
        return bytes;
    }

    //------------------------------------------------------------
	private byte[] modifyClass_Path(String name, String transformedName, byte[] bytes)// throws IOException
    {
    	ClassNode classNode = new ClassNode();
    	ClassReader classReader = new ClassReader(bytes);
    	classReader.accept(classNode, 0);
    	
    	boolean is18 = name.equals("bnl");
		String addComponentParts			= "func_74875_a";
		String World 						= "net.minecraft.world.World";
		String StructureBoundingBox 		= "net.minecraft.world.gen.structure.StructureBoundingBox";
		String oThis						= is18 ? "bnl" : "bnj";
		String oWorld 						= is18 ? "aqu" : "aqr";
		String oStructureBoundingBox 		= is18 ? "bjb" : "biz";
		
		String descFormat	= "(L%s;Ljava/util/Random;L%s;)Z";
		
		String mname = addComponentParts;
		String mdesc = String.format(descFormat, World, StructureBoundingBox).replaceAll("\\.", "/");
		
		String oname = "a";
		String odesc = String.format(descFormat, oWorld, oStructureBoundingBox).replaceAll("\\.", "/");

		byte[] templateClass = ASMHelper.loadclass(MVBCore.location.getPath(), "mvb/MVBAsmTemplate.class");
		templateClass = ASMHelper.remapClass(templateClass, "mvb/MVBAsmTemplate", oThis);
		if(templateClass == null) {
			System.out.println("Template class not found.");
			return bytes;
		}
		MethodNode newMethod = ASMHelper.cloneMethod(templateClass, mname, mdesc);
		if(newMethod == null) {
			newMethod = ASMHelper.cloneMethod(templateClass, oname, odesc);
			if(newMethod == null){
				System.out.println("Template "+mname+mdesc+" not found.");
				return bytes;
			}
		}

    	Iterator<MethodNode> methods = classNode.methods.iterator();
    	while(methods.hasNext()) {
    		MethodNode m = methods.next();
    		//System.out.println("view : [" + m.name + "][" + m.desc + "]");
    		if (m.name.equals(oname) && (m.desc.equals(odesc)))
    		{
    			methods.remove();
    			classNode.methods.add(newMethod);
        		System.out.println("Method replaced : " + mname);
    			break;
    		}
    	}

    	ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
    	classNode.accept(writer);

    	byte[] buf = writer.toByteArray();

    	//System.out.println("[MVB+]: "+ transformedName +"(" + name + ") Modified: " + String.valueOf(buf.length) + " Bytes");
    	//ASMHelper.exportClass(name, buf);
 
    	return buf;
        //return bytes;
    }

    //------------------------------------------------------------
    private byte[] modifyClass_village(String name, String transformedName, byte[] bytes)// throws IOException
    {
    	ClassNode classNode = new ClassNode();
    	ClassReader classReader = new ClassReader(bytes);
    	classReader.accept(classNode, 0);
    	
    	boolean is18 = name.equals("bnn");
    	String getAverageGroundLevel		= "func_74889_b";
		String World 						= "net.minecraft.world.World";
		String StructureBoundingBox 		= "net.minecraft.world.gen.structure.StructureBoundingBox";
		String BlockPos 					= "net.minecraft.util.BlockPos";
		String BiomeGenBase 				= "net.minecraft.world.biome.BiomeGenBase";
		String Start						= "net.minecraft.world.gen.structure.StructureVillagePieces$Start";
		String IBlockState					= "net.minecraft.block.state.IBlockState";
		String GetVillageBlockID			= "net.minecraftforge.event.terraingen.BiomeEvent$GetVillageBlockID";
		String EnumFacing					= "net/minecraft/util/EnumFacing";
		
		String oThis						= is18 ? "bnn" : "bnl";
		String oWorld 						= is18 ? "aqu" : "aqr";
		String oStructureBoundingBox 		= is18 ? "bjb" : "biz";
		String oBlockPos 					= is18 ? "dt" : "dt";
		String oBiomeGenBase 				= is18 ? "arm" : "arj";
		String oStart						= is18 ? "bnk" : "bni";
		String oIBlockState					= is18 ? "bec" : "bea";
		String oEnumFacing					= is18 ? "ej" : "ej";

		String descFormat	= "(L%s;L%s;)I";

		String mname = getAverageGroundLevel;
		String mdesc = String.format(descFormat, World, StructureBoundingBox);
		
		String oname = "b";
		String odesc = String.format(descFormat, oWorld, oStructureBoundingBox);
		
		//System.out.println(mname + mdesc);

		byte[] templateClass = ASMHelper.loadclass(MVBCore.location.getPath(), "mvb/MVBAsmTemplate.class");
		templateClass = ASMHelper.remapClass(templateClass, "mvb/MVBAsmTemplate", oThis);
		if(templateClass == null) {
			System.out.println("Template class not found.");
			return bytes;
		}
		
		MethodNode newMethod = ASMHelper.cloneMethod(templateClass, mname, mdesc);
		if(newMethod == null) {
			newMethod = ASMHelper.cloneMethod(templateClass, oname, odesc);
			if(newMethod == null) {
				System.out.println("Template "+mname+mdesc+" not found.");
				return bytes;
			}
		}
    	
		//replace getAverageGroundLevel
    	Iterator<MethodNode> methods = classNode.methods.iterator();
    	while(methods.hasNext()) {
    		MethodNode m = methods.next(); 
    		if (m.name.equals(oname) && (m.desc.equals(odesc)))
    		{
    			methods.remove();
    			classNode.methods.add(newMethod);
        		System.out.println("Method replaced : " + mname);
    			break;
    		}
    	}

    	{
    		//add biome field
    		classNode.fields.add(new FieldNode(ACC_PRIVATE, "biome", "L"+oBiomeGenBase+";", null, null));
    		System.out.println("Field added : biome (FIX for MC-32514)");

			//put initialize in <init>
	    	String mname2 = "Village";
	    	String oname2 = "<init>";
	    	String mdesc2 = "(L"+Start+";I)V";
	    	String odesc2 = "(L"+oStart+";I)V";
	    	MethodNode newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, mdesc2);
			if(newMethod2 == null) {
				newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, odesc2);
				if (newMethod2 == null) {
					System.out.println("Template "+mname2+mdesc2+" not found.");
					return bytes;
				}
			}
	    	methods = classNode.methods.iterator();
	    	while(methods.hasNext()) {
	    		MethodNode m = methods.next();
	    		if (m.name.equals(oname2) && m.desc.equals(odesc2)) {
	    			int chk = 0;
	    			AbstractInsnNode insn = null;
	    			Iterator < AbstractInsnNode > insns = m.instructions.iterator();
	    			while (insns.hasNext()) {
	    			    insn = insns.next();
	    			    if (chk == 0 && insn.getType() == VAR_INSN && insn.getOpcode() == ALOAD && ((VarInsnNode)insn).var == 0) { chk++; continue; }
	    			    if (chk == 1 && insn.getType() == VAR_INSN && insn.getOpcode() == ALOAD && ((VarInsnNode)insn).var == 1) { chk++; continue; }
	    			    if (chk == 2 && insn.getType() == FIELD_INSN  && ASMHelper.isFieldEqual(insn, GETFIELD, oStart, "b", "Z")){ chk++; continue; }
	    			    if (chk == 3 && insn.getType() == FIELD_INSN  && ASMHelper.isFieldEqual(insn, PUTFIELD, oThis, "b", "Z")){ chk++; continue; }
	    			    if (chk == 4){
	    	    			m.instructions.insertBefore(insn, ASMHelper.getPreReturnInsnList(newMethod2));
	    	        		System.out.println("Method modified : " + oname2 + " (FIX for MC-32514)");
	    	    			break;
	    			    }
	    			    chk = 0;
	    			}
	    			break;
	    		}
	    	}
    	}
    	
    	{

			//func_175847_a  refresh the field 'biome' 
	    	String mname2 = "A";
	    	String mdesc2 = "(L"+IBlockState+";)V";
	    	String oname2 = "a";
	    	String odesc2 = "(L"+oIBlockState+";)L"+oIBlockState+";";
	    	MethodNode newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, mdesc2);
			if(newMethod2 == null) {
				System.out.println("Template "+mname2+mdesc2+" not found.");
				return bytes;
			}
        	methods = classNode.methods.iterator();
        	while(methods.hasNext()) {
        		MethodNode m = methods.next(); 
        		//System.out.println("view : [" + m.name + "][" + m.desc + "]");
        		if (m.name.equals(oname2) && (m.desc.equals(odesc2)))
        		{
	    			int chk = 0;
	    			AbstractInsnNode insn = null;
	    			InsnList addList = new InsnList();
	    			Iterator < AbstractInsnNode > insns = m.instructions.iterator();
	    			while (insns.hasNext()) {
	    			    insn = insns.next();
	    			    if(chk == 0 && insn.getType() == TYPE_INSN && insn.getOpcode() == NEW){
	    			    	addList.add(ASMHelper.getPreReturnInsnList(newMethod2));
	    			    	chk = 1;
	    			    } else
	    			    if(chk == 1 && insn.getType() == METHOD_INSN && ASMHelper.isMethodEqual(insn, INVOKESPECIAL, GetVillageBlockID, "<init>", "(L"+oBiomeGenBase+";L"+oIBlockState+";)V")) {
	    			    	chk = 2;
	    			    	if (insns.hasNext()) insn = insns.next();
	    			    	if (insns.hasNext()) insn = insns.next();
	    			    }
	    			    if(chk == 0 || chk == 2) {
		    	        	//System.out.println(insn + ":" + ASMHelper.opcode2str.get(insn.getOpcode()));
	    			    	addList.add(insn);
	    			    }
	    			}
	    			m.instructions.clear();
	    			m.instructions.add(addList);
	        		System.out.println("Method modified : func_175847_a" + " (FIX for MC-32514)");
        			break;
        		}
        	}
    	}

    	{
	    	//BlockPos getTopSolidOrLiquidBlock(World var0, BlockPos var1)
	    	String mname2 = "getTopSolidOrLiquidBlock";
	    	String mdesc2 = "(L"+World+";L"+BlockPos+";)L"+BlockPos+";";
	    	String odesc2 = "(L"+oWorld+";L"+oBlockPos+";)L"+oBlockPos+";";
	    	MethodNode newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, mdesc2);
			if(newMethod2 == null) {
				newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, odesc2);
				if (newMethod2 == null) {
					System.out.println("Template "+mname2+mdesc2+" not found.");
					return bytes;
				}
			}
	    	classNode.methods.add(newMethod2);
    		System.out.println("Method (original) added : " + mname2);
    	}

    	{
	    	//BiomeGenBase GetBiome()
	    	String mname2 = "getBiome";
	    	String mdesc2 = "()L"+BiomeGenBase+";";
	    	String odesc2 = "()L"+oBiomeGenBase+";";
	    	MethodNode newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, mdesc2);
			if(newMethod2 == null) {
				newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, odesc2);
				if (newMethod2 == null) {
					System.out.println("Template "+mname2+mdesc2+" not found.");
					return bytes;
				}
			}
	    	classNode.methods.add(newMethod2);
    		System.out.println("Method (original) added : " + mname2 + " (FIX for MC-32514)");
    	}

    	{
	    	//void SetBiome(BiomeGenBase biome)
	    	String mname2 = "setBiome";
	    	String mdesc2 = "(L"+BiomeGenBase+";)V";
	    	String odesc2 = "(L"+oBiomeGenBase+";)V";
	    	MethodNode newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, mdesc2);
			if(newMethod2 == null) {
				newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, odesc2);
				if (newMethod2 == null) {
					System.out.println("Template "+mname2+mdesc2+" not found.");
					return bytes;
				}
			}
	    	classNode.methods.add(newMethod2);
    		System.out.println("Method (original) added : " + mname2 + " (FIX for MC-32514)");
    	}
    	
    	boolean isHard = false;
    	try {
    		isHard = SaferVillage.Plugin.isHARD;
    	} catch (NoClassDefFoundError e) {
    	}
    	
    	if(!isHard){
	    	//func_175810_a(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, EnumFacing var7)
	    	String mname2 = "func_175810_a";
	    	String oname2 = "a";
	    	String mdesc2 = "(L"+World+";L"+StructureBoundingBox+";Ljava/util/Random;IIIL"+EnumFacing+";)V";
	    	String odesc2 = "(L"+oWorld+";L"+oStructureBoundingBox+";Ljava/util/Random;IIIL"+oEnumFacing+";)V";
	    	MethodNode newMethod2 = ASMHelper.cloneMethod(templateClass, mname2, mdesc2);
			if(newMethod2 == null) {
				newMethod2 = ASMHelper.cloneMethod(templateClass, oname2, odesc2);
				if (newMethod2 == null) {
					System.out.println("Template "+mname2+mdesc2+" not found.");
					return bytes;
				}
			}
	    	classNode.methods.add(newMethod2);
    		System.out.println("Method (super) added : " + mname2);
    	}

    	ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
    	classNode.accept(writer);

    	byte[] buf = writer.toByteArray();

    	//System.out.println("[MVB+]: "+ transformedName +"(" + name + ") Modified: " + String.valueOf(buf.length) + " Bytes");
    	//ASMHelper.exportClass(name, buf);
    	 
    	return buf;
        //return bytes;
    }

    //------------------------------------------------------------
    private byte[] modifyClass_Start(String name, String transformedName, byte[] bytes)
    {
    	ClassNode classNode = new ClassNode();
    	ClassReader classReader = new ClassReader(bytes);
    	classReader.accept(classNode, 0);
    	
    	boolean is18 = name.equals("bnk");
		String Start						= "Start";
		String WorldChunkManager			= "net.minecraft.world.biome.WorldChunkManager";
		String StructureBoundingBox 		= "net.minecraft.world.gen.structure.StructureBoundingBox";
		String oStart						= is18 ? "bnk" : "bni";
		String oWorldChunkManager			= is18 ? "arz" : "arw";
		String oStructureBoundingBox 		= is18 ? "bjb" : "biz";
		
		String descFormat = "(L%s;ILjava/util/Random;IILjava/util/List;I)V";
		
		String mname = Start;
		String mdesc = String.format(descFormat, WorldChunkManager);
		String oname = "<init>";
		String odesc = String.format(descFormat, oWorldChunkManager);
		
		byte[] templateClass = ASMHelper.loadclass(MVBCore.location.getPath(), "mvb/MVBAsmTemplate.class");
		templateClass = ASMHelper.remapClass(templateClass, "mvb/MVBAsmTemplate", oStart);
		if(templateClass == null) {
			System.out.println("Template class not found.");
			return bytes;
		}
		
		MethodNode newMethod = ASMHelper.cloneMethod(templateClass, mname, mdesc);
		if(newMethod == null) {
			newMethod = ASMHelper.cloneMethod(templateClass, mname, odesc);
			if(newMethod == null) {
				System.out.println("Template "+mname+mdesc+" not found.");
				return bytes;
			}
		}

    	Iterator<MethodNode> methods = classNode.methods.iterator();
    	while(methods.hasNext()) {
    		MethodNode m = methods.next();
    		//System.out.println("view : [" + m.name + "][" + m.desc + "]");
    		if (m.name.equals(oname) && (m.desc.equals(odesc)))
    		{
    			m.instructions.insertBefore(ASMHelper.getPreReturnInsn(m), ASMHelper.getPreReturnInsnList(newMethod));
        		System.out.println("Method modified : " + oname + " (FIX for MC-32514)");
    			break;
    		}
    	}

    	ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
    	classNode.accept(writer);

    	byte[] buf = writer.toByteArray();

    	//System.out.println("[MVB+]: "+ transformedName +"(" + name + ") Modified: " + String.valueOf(buf.length) + " Bytes");
    	//ASMHelper.exportClass(name, buf);
 
    	return buf;
        //return bytes;
	}

    //------------------------------------------------------------
    private byte[] modifyClass_Well(String name, String transformedName, byte[] bytes)
    {

    	ClassNode classNode = new ClassNode();
    	ClassReader classReader = new ClassReader(bytes);
    	classReader.accept(classNode, 0);
    	
    	boolean is18 = name.equals("bnp");
		String addComponentParts			= "func_74875_a";
		String World 						= "net.minecraft.world.World";
		String StructureBoundingBox 		= "net.minecraft.world.gen.structure.StructureBoundingBox";
		String oaddComponentParts			= "a";
		String _this						= is18 ? "bnp" : "bnn";
		String oWorld 						= is18 ? "aqu" : "aqr";
		String oStructureBoundingBox 		= is18 ? "bjb" : "biz";
		String oBlock 						= is18 ? "atr" : "ato";
		String oBlocks 						= is18 ? "aty" : "atv";
		String oIBlockState					= is18 ? "bec" : "bea";
		
		String mname = oaddComponentParts;
		String mdesc = "(L"+oWorld+";Ljava/util/Random;L"+oStructureBoundingBox+";)Z";
    	
    	Iterator<MethodNode> methods = classNode.methods.iterator();
    	while(methods.hasNext())
    	{
    		MethodNode m = methods.next();
    		if (m.name == null || m.desc == null) continue;

    		//System.out.println("view : [" + m.name + "][" + m.desc + "]");
    		if (m.name.equals(mname) && (m.desc.equals(mdesc)))
    		{
    			 int chk = 0;
	             AbstractInsnNode insn = null;
	             Iterator < AbstractInsnNode > insns = m.instructions.iterator();
	             while (insns.hasNext()) {
	                insn = insns.next();
	                if (chk == 0 && insn.getType() == FRAME && ((FrameNode)insn).type == F_SAME) { chk++; continue; }
	                if (chk == 1 && insn.getType() == VAR_INSN && insn.getOpcode() == ALOAD && ((VarInsnNode)insn).var == 0) { chk++; continue; }
	                if (chk == 2 && insn.getType() == VAR_INSN && insn.getOpcode() == ALOAD && ((VarInsnNode)insn).var == 1) { chk++; continue; }
	                if (chk == 3 && insn.getType() == FIELD_INSN  && ASMHelper.isFieldEqual(insn, GETSTATIC, oBlocks, "n", "L"+oBlock+";")){ chk++; continue; }
	                if (chk == 4 && insn.getType() == METHOD_INSN && ASMHelper.isMethodEqual(insn, INVOKEVIRTUAL, oBlock, "P", "()L"+oIBlockState+";")){ chk++; continue; }
	                if (chk == 5 && insn.getType() == VAR_INSN && insn.getOpcode() == ILOAD && ((VarInsnNode)insn).var == 5) { chk++; continue; }
	                if (chk == 6 && insn.getType() == INT_INSN && insn.getOpcode() == BIPUSH && ((IntInsnNode)insn).operand == 11) { chk++; continue; }
	                if (chk == 7 && insn.getType() == VAR_INSN && insn.getOpcode() == ILOAD && ((VarInsnNode)insn).var == 4) { chk++; continue; }
	                if (chk == 8 && insn.getType() == VAR_INSN && insn.getOpcode() == ALOAD && ((VarInsnNode)insn).var == 3) { chk++; continue; }
	                if (chk == 9 && insn.getType() == METHOD_INSN && ASMHelper.isMethodEqual(insn, INVOKEVIRTUAL, _this, "a", "(L"+oWorld+";L"+oIBlockState+";IIIL"+oStructureBoundingBox+";)V")){ chk++; continue; }
	                if (chk == 10) {
	                	InsnList addlist = new InsnList();
	                	addlist.add(new VarInsnNode(ALOAD, 0));
	                	addlist.add(new VarInsnNode(ALOAD, 1));
	                	addlist.add(new FieldInsnNode(GETSTATIC, oBlocks, "e", "L"+oBlock+";"));
	                	addlist.add(new MethodInsnNode(INVOKEVIRTUAL, oBlock, "P", "()L"+oIBlockState+";", false));
	                	addlist.add(new VarInsnNode(ILOAD, 5));
	                	addlist.add(new IntInsnNode(BIPUSH, 10));
	                	addlist.add(new VarInsnNode(ILOAD, 4));
	                	addlist.add(new VarInsnNode(ALOAD, 3));
	                	addlist.add(new MethodInsnNode(INVOKEVIRTUAL, _this, "a", "(L"+oWorld+";L"+oIBlockState+";IIIL"+oStructureBoundingBox+";)V", false));
	                	m.instructions.insertBefore(insn, addlist);
	            		System.out.println("Method modified : " + addComponentParts);
	                	break;
	                }
	                chk = 0;
	             }
	     		break;
    		}
       }

    	ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
    	classNode.accept(writer);

    	byte[] buf = writer.toByteArray();

    	//System.out.println("[MVB+]: "+ transformedName +"(" + name + ") Modified: " + String.valueOf(buf.length) + " Bytes");
    	//ASMHelper.exportClass(name, buf);
 
    	return buf;
        //return bytes;
    }
}