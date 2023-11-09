import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main
{
    public static void main(String[] args)
    {
        // кнопка выбора цвета
        JButton colorButton = new JButton();
        colorButton.setBounds(100, 10, 60, 60);
        colorButton.setBackground(Color.BLACK);
        colorButton.addActionListener(e ->
        {
            Color newColor = JColorChooser.showDialog(null, "Выберите цвет", Color.BLACK);
            if (newColor != null)
            {
                colorButton.setBackground(newColor);
            }
        });

        // кнопка выбора толщины
        String[] thicknessOptions = {"1", "3", "5", "7", "9", "11"}; // опции толщины пера
        JLabel thicknessLabel = new JLabel("Толщина:");
        thicknessLabel.setBounds(320, 20, 80, 40);
        JComboBox<String> thicknessComboBox = new JComboBox<>(thicknessOptions);
        thicknessComboBox.setBounds(400, 20, 120, 40);

        // верхняя панель меню
        JPanel menuPanel = new JPanel(null);
        menuPanel.setBounds(0, 0, 650, 80);
        menuPanel.setBackground(new Color(0xe8e8e8));
        //
        menuPanel.add(colorButton);
        menuPanel.add(thicknessLabel);
        menuPanel.add(thicknessComboBox);

        // нижняя панель для рисования
        JPanel paintPanel = new JPanel(null);
        paintPanel.setBounds(0, menuPanel.getHeight(), 650, 560);

        // добавляем канвас для обработки событий мыши
        Canvas canvas = new Canvas();
        canvas.setBounds(0, 0, paintPanel.getWidth(), paintPanel.getHeight());
        canvas.setBackground(Color.WHITE);
        canvas.addMouseListener(new MouseAdapter()
        {
            int x1, y1;

            @Override
            public void mousePressed(MouseEvent e)
            {
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                int x2 = e.getX();
                int y2 = e.getY();

                // рисование линии заданной толщины и заданного цвета в панели меню
                Graphics2D g = (Graphics2D) canvas.getGraphics();
                g.setColor(colorButton.getBackground());
                int width = Integer.parseInt((String) thicknessComboBox.getSelectedItem());
                g.setStroke(new BasicStroke(width));
                g.drawLine(x1, y1, x2, y2);
            }
        });
        paintPanel.add(canvas);

        // выводимое окно
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(paintPanel.getWidth(), paintPanel.getHeight() + menuPanel.getHeight());
        frame.setResizable(false);
        frame.setTitle("Drawing");
        frame.setIconImage(new ImageIcon("pero.png").getImage());
        frame.setLayout(null);
        //
        frame.add(menuPanel);
        frame.add(paintPanel);
        //
        frame.setVisible(true);
    }
}