package gui;

import utils.ImageLoader;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class PuzzleTileCellRenderer extends JLabel implements TableCellRenderer {

    public PuzzleTileCellRenderer() {
        setBackground(Color.WHITE);
        setOpaque(true);
        setFont(new Font("Monospaced", Font.BOLD, 49));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus, int row,
                                                   int column) {

//        String text = (((Integer) value).intValue() == 0) ? "" : ((Integer) value).toString();
//        setText(text);

        ImageLoader loader = ImageLoader.getLoader();
        setText("");

        if (((Integer) value).intValue() == 1) {
            setIcon(loader.getIcon(Properties.EMPILHADORA_IMAGE));
        }else if(((Integer) value).intValue() == -1){
            setIcon(loader.getIcon(Properties.PORTA_IMAGE));
        } else if (((Integer) value).intValue() == 0) {
            setIcon(loader.getIcon(Properties.EMPTY_IMAGE));
        } else {
            setIcon(loader.getIcon(
                    Properties.IMAGE_PREFIX + calculaOrientacaoPrefixo(((Integer) value).intValue()) + Properties.IMAGE_SUFFIX));
        }
        return this;
    }

    private String calculaOrientacaoPrefixo(Integer numero){
        String orientacao = "";
        String numeroString = "";
        Integer divisao = numero / 2;
        //System.out.print("numero = "+ numero + "\tdivisao = " + divisao + "\t");

        if (divisao != 1) {
            numeroString = divisao.toString();
        } else {
            numeroString = "";
        }
        if (numero % 2 == 0)
        {
            orientacao = "H";
        }
        else{
            orientacao = "V";
        }
        return orientacao + numeroString;
    }
}
