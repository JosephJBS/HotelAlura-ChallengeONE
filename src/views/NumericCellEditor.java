package views;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumericCellEditor extends DefaultCellEditor {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

    public NumericCellEditor() {
        super(new JTextField());
        this.textField = (JTextField) getComponent();
        textField.setDocument(new NumericDocument()); // Usa nuestro PlainDocument personalizado

    }

    @Override
    public Object getCellEditorValue() {
        return textField.getText();
    }

    private class NumericDocument extends PlainDocument {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            // Asegura que solo se ingresen números
            if (str.matches("\\d*")) {
                super.insertString(offset, str, attr);
            }
        }
    }
}