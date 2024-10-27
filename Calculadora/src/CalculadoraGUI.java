import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1, num2, resultado;
    private String operador = "";  // Inicializa como cadena vacía

    public CalculadoraGUI() {
        // Configuración de la ventana
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el campo de texto
        textField = new JTextField();
        add(textField, BorderLayout.NORTH);

        // Panel para los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        // Crear botones
        String[] botones = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : botones) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ("0123456789".contains(comando)) {
            textField.setText(textField.getText() + comando);
        } else if (comando.equals("C")) {
            textField.setText("");
            num1 = num2 = resultado = 0;
            operador = ""; // Reinicia el operador
        } else if (comando.equals("=")) {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (operador) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            resultado = num1 / num2;
                        } else {
                            textField.setText("Error");
                            return;
                        }
                        break;
                }
                // Mostrar el resultado sin punto decimal si es un entero
                if (resultado == (int) resultado) {
                    textField.setText(String.valueOf((int) resultado));
                } else {
                    textField.setText(String.valueOf(resultado));
                }
                operador = "";  // Reinicia el operador después de calcular
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        } else {
            if (operador.isEmpty()) {  // Verifica si el operador ya está asignado
                operador = comando;
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraGUI calculadora = new CalculadoraGUI();
            calculadora.setVisible(true);
        });
    }
}
