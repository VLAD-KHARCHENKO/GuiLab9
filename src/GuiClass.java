import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiClass extends JFrame {
    private JButton button = new JButton("Send");
    public JTextField input = new JTextField();
    private JTextField input1 = new JTextField("", 40);
    private JTextField input2 = new JTextField("", 10);
    private JTextField input3 = new JTextField("", 10);
    private JTextField input4 = new JTextField("", 10);
    private JTextField input5 = new JTextField("", 10);
    private JLabel label = new JLabel("Введіть параметр а1:");
    private JLabel label1 = new JLabel("Введіть параметр b1:");
    private JLabel label2 = new JLabel("Введіть параметр c1:");
    private JLabel label3 = new JLabel("Введіть параметр а2:");
    private JLabel label4 = new JLabel("Введіть параметр b2:");
    private JLabel label5 = new JLabel("Введіть параметр c2:");

    public GuiClass() {
        super("Lab9");
        this.setBounds(50, 20, 500, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(7, 0, 1, 1));
        container.add(label);
        container.add(input);
        System.out.println();
        container.add(label1);
        container.add(input1);
        container.add(label2);
        container.add(input2);
        container.add(label3);
        container.add(input3);
        container.add(label4);
        container.add(input4);
        container.add(label5);
        container.add(input5);
        button.addActionListener(new ButtonEventLisiner());
        container.add(button);
    }

    class ButtonEventLisiner implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Double[] arr = new Double[6];
            Double a = Double.parseDouble(input.getText());
            Double a1 = Double.parseDouble(input1.getText());
            Double a2 = Double.parseDouble(input2.getText());
            Double a3 = Double.parseDouble(input3.getText());
            Double a4 = Double.parseDouble(input4.getText());
            Double a5 = Double.parseDouble(input5.getText());
            String message = "";
            arr[0] = a;
            arr[1] = a1;
            arr[2] = a2;
            arr[3] = a3;
            arr[4] = a4;
            arr[5] = a5;

            /* Ввод данных */

            int n = 2;
            int m = 2;
            double[][] A = new double[100][100];
            double[] b = new double[100];
            int count = 0;
            for (int i = 0; i < n; i++) {
                //  A[i] = new double[100];
                for (int j = 0; j < m; j++) {
                    A[i][j] = arr[count];
                    count++;
                }
                b[i] = arr[count];
                count++;
            }

            /* Метод Гаусса */

            int N = n;
            for (int p = 0; p < N; p++) {

                int max = p;
                for (int i = p + 1; i < N; i++) {
                    if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                        max = i;
                    }
                }
                double[] temp = A[p];
                A[p] = A[max];
                A[max] = temp;
                double t = b[p];
                b[p] = b[max];
                b[max] = t;

                if (Math.abs(A[p][p]) <= 1e-10) {
                    // System.out.println("NO");
                    message = "NO Results";

                }

                for (int i = p + 1; i < N; i++) {
                    double alpha = A[i][p] / A[p][p];
                    b[i] -= alpha * b[p];
                    for (int j = p; j < N; j++) {
                        A[i][j] -= alpha * A[p][j];
                    }
                }
            }

            // Обратный проход

            double[] x = new double[N];
            for (int i = N - 1; i >= 0; i--) {
                double sum = 0.0;
                for (int j = i + 1; j < N; j++) {
                    sum += A[i][j] * x[j];
                }
                x[i] = (b[i] - sum) / A[i][i];
            }

            /* Вывод результатов */
            StringBuffer stringBuffer = new StringBuffer();
            if (n < m) {
                System.out.print("INF");
            } else {
                stringBuffer.append("Results: ");
                // System.out.println("YES");
                for (int i = 0; i < N; i++) {
                    // System.out.print(x[i] + " ");
                    stringBuffer.append(x[i]).append(" ");


                }
            }
            String str =stringBuffer.toString();
            message = str;

            JOptionPane.showMessageDialog(null, message, "Результат", JOptionPane.PLAIN_MESSAGE);
        }

            // System.out.println(arr[0]);


        }


    }


