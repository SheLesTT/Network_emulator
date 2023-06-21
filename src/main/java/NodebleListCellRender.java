import javax.swing.*;
import java.awt.*;

  class NodebleListCellRender extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){

        JLabel component = (JLabel) super.getListCellRendererComponent(list,value, index, isSelected,cellHasFocus);
        if(value instanceof Nodeble){
            component.setText(((Nodeble)value).getName());
//                value = ((Nodeble) value).getName();

            if (((Nodeble)value).getSleep()) {
                component.setForeground(Color.GRAY);
            }
        }
        return component;
    }

}
