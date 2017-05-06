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
        }
        else{
            System.out.println(calculaOrientacaoPrefixo(((Integer) value).intValue()));
            setIcon(loader.getIcon(Properties.EMPTY_IMAGE));
        }

//        if (((Integer) value).intValue() == 0) {
//            setIcon(loader.getIcon(Properties.EMPTY_IMAGE));
//        } else {
//            if (((Integer) value).intValue() == 1) {
//                setIcon(loader.getIcon(Properties.EMPILHADORA_IMAGE));
//            }else{
//                if (((Integer) value).intValue() == 10) {
//                    setIcon(loader.getIcon(Properties.PORTA_IMAGE));
//                }
//            }
//            //setIcon(loader.getIcon(Properties.IMAGE_PREFIX + ((Integer) value).intValue() + Properties.IMAGE_SUFFIX));
//        }
        return this;
    }

    private String calculaOrientacaoPrefixo(Integer numero){
        String orientacao = "";
        Integer divisao = numero / 2;
        String numeroString = "";
        System.out.print("numero = "+ numero + "\tdivisao = " + divisao);
        if (divisao != 1 || divisao != 0) {
            numeroString = divisao.toString() ;
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
