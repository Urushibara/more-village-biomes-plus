package mvb;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class MVBBlock {
	private static Block Id2Block(int id)
	{
		Block ret = Block.getBlockById(id); 
		return ret==null ? Block.getBlockById(0): ret;
	}
	
	public static IBlockState Meta(Block block, int DataValue)
	{	
		return block.getStateFromMeta(DataValue);
	}
	
	public static IBlockState Cnv(IBlockState src, Block dst)
	{
		PropertyDirection FACING = BlockStairs.FACING;
		return dst.getDefaultState().withProperty(FACING, src.getValue(FACING));
	}

	//
	public static int WHITE = 0;
	public static int ORANGE = 1;
	public static int MAGENTA = 2;
	public static int LIGHT_BLUE = 3;
	public static int YELLOW = 4;
	public static int LIME = 5;
	public static int PINK = 6;
	public static int GRAY = 7;
	public static int SILVER = 8;
	public static int CYAN = 9;
	public static int PURPLE = 10;
	public static int BLUE = 11;
	public static int BROWN = 12;
	public static int GREEN = 13;
	public static int RED = 14;
	public static int BLACK = 15;
	//
	public static int OAK = 0;
	public static int SPRUCE = 1;
	public static int BIRCH = 2;
	public static int JUNGLE = 3;
	public static int ACACIA = 4;
	public static int DARK_OAK = 5;
	
	public static final Block air = Id2Block(0);
	public static final Block stone = Id2Block(1);
	public static final Block grass = Id2Block(2);
	public static final Block dirt = Id2Block(3);
	public static final Block cobblestone = Id2Block(4);
	public static final Block planks = Id2Block(5);
	public static final Block sapling = Id2Block(6);
	public static final Block bedrock = Id2Block(7);
	public static final Block flowing_water = Id2Block(8);
	public static final Block water = Id2Block(9);
	public static final Block flowing_lava = Id2Block(10);
	public static final Block lava = Id2Block(11);
	public static final Block sand = Id2Block(12);
	public static final Block gravel = Id2Block(13);
	public static final Block gold_ore = Id2Block(14);
	public static final Block iron_ore = Id2Block(15);
	public static final Block coal_ore = Id2Block(16);
	public static final Block log = Id2Block(17);
	public static final Block leaves = Id2Block(18);
	public static final Block sponge = Id2Block(19);
	public static final Block glass = Id2Block(20);
	public static final Block lapis_ore = Id2Block(21);
	public static final Block lapis_block = Id2Block(22);
	public static final Block dispenser = Id2Block(23);
	public static final Block sandstone = Id2Block(24);
	public static final Block noteblock = Id2Block(25);
	public static final Block bed = Id2Block(26);
	public static final Block golden_rail = Id2Block(27);
	public static final Block detector_rail = Id2Block(28);
	public static final Block sticky_piston = Id2Block(29);
	public static final Block web = Id2Block(30);
	public static final Block tallgrass = Id2Block(31);
	public static final Block deadbush = Id2Block(32);
	public static final Block piston = Id2Block(33);
	public static final Block piston_head = Id2Block(34);
	public static final Block wool = Id2Block(35);
	public static final Block piston_extension = Id2Block(36);
	public static final Block yellow_flower = Id2Block(37);
	public static final Block red_flower = Id2Block(38);
	public static final Block brown_mushroom = Id2Block(39);
	public static final Block red_mushroom = Id2Block(40);
	public static final Block gold_block = Id2Block(41);
	public static final Block iron_block = Id2Block(42);
	public static final Block double_stone_slab = Id2Block(43);
	public static final Block stone_slab = Id2Block(44);
	public static final Block brick_block = Id2Block(45);
	public static final Block tnt = Id2Block(46);
	public static final Block bookshelf = Id2Block(47);
	public static final Block mossy_cobblestone = Id2Block(48);
	public static final Block obsidian = Id2Block(49);
	public static final Block torch = Id2Block(50);
	public static final Block fire = Id2Block(51);
	public static final Block mob_spawner = Id2Block(52);
	public static final Block oak_stairs = Id2Block(53);
	public static final Block chest = Id2Block(54);
	public static final Block redstone_wire = Id2Block(55);
	public static final Block diamond_ore = Id2Block(56);
	public static final Block diamond_block = Id2Block(57);
	public static final Block crafting_table = Id2Block(58);
	public static final Block wheat = Id2Block(59);
	public static final Block farmland = Id2Block(60);
	public static final Block furnace = Id2Block(61);
	public static final Block lit_furnace = Id2Block(62);
	public static final Block standing_sign = Id2Block(63);
	public static final Block wooden_door = Id2Block(64);
	public static final Block ladder = Id2Block(65);
	public static final Block rail = Id2Block(66);
	public static final Block stone_stairs = Id2Block(67);
	public static final Block wall_sign = Id2Block(68);
	public static final Block lever = Id2Block(69);
	public static final Block stone_pressure_plate = Id2Block(70);
	public static final Block iron_door = Id2Block(71);
	public static final Block wooden_pressure_plate = Id2Block(72);
	public static final Block redstone_ore = Id2Block(73);
	public static final Block lit_redstone_ore = Id2Block(74);
	public static final Block unlit_redstone_torch = Id2Block(75);
	public static final Block redstone_torch = Id2Block(76);
	public static final Block stone_button = Id2Block(77);
	public static final Block snow_layer = Id2Block(78);
	public static final Block ice = Id2Block(79);
	public static final Block snow = Id2Block(80);
	public static final Block cactus = Id2Block(81);
	public static final Block clay = Id2Block(82);
	public static final Block reeds = Id2Block(83);
	public static final Block jukebox = Id2Block(84);
	public static final Block fence = Id2Block(85);
	public static final Block pumpkin = Id2Block(86);
	public static final Block netherrack = Id2Block(87);
	public static final Block soul_sand = Id2Block(88);
	public static final Block glowstone = Id2Block(89);
	public static final Block portal = Id2Block(90);
	public static final Block lit_pumpkin = Id2Block(91);
	public static final Block cake = Id2Block(92);
	public static final Block unpowered_repeater = Id2Block(93);
	public static final Block powered_repeater = Id2Block(94);
	public static final Block stained_glass = Id2Block(95);
	public static final Block trapdoor = Id2Block(96);
	public static final Block monster_egg = Id2Block(97);
	public static final Block stonebrick = Id2Block(98);
	public static final Block brown_mushroom_block = Id2Block(99);
	public static final Block red_mushroom_block = Id2Block(100);
	public static final Block iron_bars = Id2Block(101);
	public static final Block glass_pane = Id2Block(102);
	public static final Block melon_block = Id2Block(103);
	public static final Block pumpkin_stem = Id2Block(104);
	public static final Block melon_stem = Id2Block(105);
	public static final Block vine = Id2Block(106);
	public static final Block fence_gate = Id2Block(107);
	public static final Block brick_stairs = Id2Block(108);
	public static final Block stone_brick_stairs = Id2Block(109);
	public static final Block mycelium = Id2Block(110);
	public static final Block waterlily = Id2Block(111);
	public static final Block nether_brick = Id2Block(112);
	public static final Block nether_brick_fence = Id2Block(113);
	public static final Block nether_brick_stairs = Id2Block(114);
	public static final Block nether_wart = Id2Block(115);
	public static final Block enchanting_table = Id2Block(116);
	public static final Block brewing_stand = Id2Block(117);
	public static final Block cauldron = Id2Block(118);
	public static final Block end_portal = Id2Block(119);
	public static final Block end_portal_frame = Id2Block(120);
	public static final Block end_stone = Id2Block(121);
	public static final Block dragon_egg = Id2Block(122);
	public static final Block redstone_lamp = Id2Block(123);
	public static final Block lit_redstone_lamp = Id2Block(124);
	public static final Block double_wooden_slab = Id2Block(125);
	public static final Block wooden_slab = Id2Block(126);
	public static final Block cocoa = Id2Block(127);
	public static final Block sandstone_stairs = Id2Block(128);
	public static final Block emerald_ore = Id2Block(129);
	public static final Block ender_chest = Id2Block(130);
	public static final Block tripwire_hook = Id2Block(131);
	public static final Block tripwire = Id2Block(132);
	public static final Block emerald_block = Id2Block(133);
	public static final Block spruce_stairs = Id2Block(134);
	public static final Block birch_stairs = Id2Block(135);
	public static final Block jungle_stairs = Id2Block(136);
	public static final Block command_block = Id2Block(137);
	public static final Block beacon = Id2Block(138);
	public static final Block cobblestone_wall = Id2Block(139);
	public static final Block flower_pot = Id2Block(140);
	public static final Block carrots = Id2Block(141);
	public static final Block potatoes = Id2Block(142);
	public static final Block wooden_button = Id2Block(143);
	public static final Block skull = Id2Block(144);
	public static final Block anvil = Id2Block(145);
	public static final Block trapped_chest = Id2Block(146);
	public static final Block light_weighted_pressure_plate = Id2Block(147);
	public static final Block heavy_weighted_pressure_plate = Id2Block(148);
	public static final Block unpowered_comparator = Id2Block(149);
	public static final Block powered_comparator = Id2Block(150);
	public static final Block daylight_detector = Id2Block(151);
	public static final Block redstone_block = Id2Block(152);
	public static final Block quartz_ore = Id2Block(153);
	public static final Block hopper = Id2Block(154);
	public static final Block quartz_block = Id2Block(155);
	public static final Block quartz_stairs = Id2Block(156);
	public static final Block activator_rail = Id2Block(157);
	public static final Block dropper = Id2Block(158);
	public static final Block stained_hardened_clay = Id2Block(159);
	public static final Block stained_glass_pane = Id2Block(160);
	public static final Block leaves2 = Id2Block(161);
	public static final Block log2 = Id2Block(162);
	public static final Block acacia_stairs = Id2Block(163);
	public static final Block dark_oak_stairs = Id2Block(164);
	public static final Block slime = Id2Block(165);
	public static final Block barrier = Id2Block(166);
	public static final Block iron_trapdoor = Id2Block(167);
	public static final Block hay_block = Id2Block(170);
	public static final Block carpet = Id2Block(171);
	public static final Block hardened_clay = Id2Block(172);
	public static final Block coal_block = Id2Block(173);
	public static final Block packed_ice = Id2Block(174);
	public static final Block double_plant = Id2Block(175);
	public static final Block standing_banner = Id2Block(176);
	public static final Block wall_banner = Id2Block(177);
	public static final Block daylight_detector_inverted = Id2Block(178);
	public static final Block red_sandstone = Id2Block(179);
	public static final Block red_sandstone_stairs = Id2Block(180);
	public static final Block double_stone_slab2 = Id2Block(181);
	public static final Block stone_slab2 = Id2Block(182);
	public static final Block spruce_fence_gate = Id2Block(183);
	public static final Block birch_fence_gate = Id2Block(184);
	public static final Block jungle_fence_gate = Id2Block(185);
	public static final Block dark_oak_fence_gate = Id2Block(186);
	public static final Block acacia_fence_gate = Id2Block(187);
	public static final Block spruce_fence = Id2Block(188);
	public static final Block birch_fence = Id2Block(189);
	public static final Block jungle_fence = Id2Block(190);
	public static final Block dark_oak_fence = Id2Block(191);
	public static final Block acacia_fence = Id2Block(192);
	public static final Block spruce_door = Id2Block(193);
	public static final Block birch_door = Id2Block(194);
	public static final Block jungle_door = Id2Block(195);
	public static final Block acacia_door = Id2Block(196);
	public static final Block dark_oak_door = Id2Block(197);
}
