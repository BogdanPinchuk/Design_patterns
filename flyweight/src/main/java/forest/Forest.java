package forest;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import trees.Tree;
import trees.TreeFactory;
import trees.TreeType;

public class Forest extends JFrame {
	private List<Tree> trees = new ArrayList<>();

	public void planTree(int x, int y, String name, Color color, String otherTreeData) {
		TreeType type = TreeFactory.getTreeType(name, color, otherTreeData);
		Tree tree = new Tree(x, y, type);
		trees.add(tree);
	}

	@Override
	public void paint(Graphics g) {
		for (Tree tree : trees) {
			tree.draw(g);
		}
	}
}
