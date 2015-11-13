package softwaredesign973.gui;

import javax.swing.*;
import java.util.List;

/**
 * Created by Jacob on 2015-11-12.
 */
public class SpinnerCircularModel extends SpinnerListModel {

    public SpinnerCircularModel(Object[] values) {
        super(values);
    }

    @Override
    public Object getNextValue() {
        List list = getList();
        int index = list.indexOf(getValue());

        index = (index >= list.size() - 1) ? 0 : index + 1;
        return list.get(index);
    }

    @Override
    public Object getPreviousValue() {
        List list = getList();
        int index = list.indexOf(getValue());

        index = (index <= 0) ? list.size() - 1 : index - 1;
        return list.get(index);
    }
}
