package sistemamimetizador;
//librerias
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class InterfazUsu extends JFrame {
    JButton btnAgregar, btnEliminar, btnActualizar;
    JPanel panel, panelSec, panelTab1, panelTab2, panelTab3;
    JLabel lbltitulo, lblTemp, lblHumedad, lblLum, lblHora, lblFecha,
            etiqueta1;
    String nom;
    JTextArea texto;
    JTextField txtTemp, txtHumedad, txtLum, txtHora, txtFecha,
            txtData, txtFec, txtTabla;
    JTabbedPane panelPest;
    JTable tabla;
    DefaultTableModel dtm;
    String columnas[] = {"No.","Mensaje"};
    String datos[][] = {};
    JScrollPane scroll,scrollTextArea;
    Date d;
    //ArrayList  sms;
    int contador,columna,fila;
    public InterfazUsu() {
        super("Sistema Mimetizador");
        this.setLayout(null);
        crear();
        armarPanelTAb();
        armarPanelOeste();
        armarVentana();
        lanzar();
    }

    private void crear() {
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");
        texto = new JTextArea();
        panel = new JPanel(null);
        panelSec = new JPanel(new BorderLayout());
        panelPest = new JTabbedPane();
        panelTab1 = new JPanel();
        panelTab2 = new JPanel();
        panelTab3 = new JPanel();
        dtm = new DefaultTableModel(datos, columnas);
        tabla = new JTable(dtm);
        tabla.setAutoCreateRowSorter(true);
        tabla.setShowGrid(true);
        tabla.setPreferredScrollableViewportSize(new Dimension(200,150));
        scroll = new JScrollPane(tabla);
        d = new Date();
        etiqueta1 = new JLabel("panel uno", SwingConstants.CENTER);
        lblFecha = new JLabel("FECHA: " + d.getDate()+ "/" + (d.getMonth()+1) +
                             "/"+ (d.getYear()+1900),SwingConstants.CENTER);
        lblHora = new JLabel("HORA: " + d.getHours() + ":" + d.getMinutes()
                             ,SwingConstants.CENTER);
        scrollTextArea = new JScrollPane(texto);
        contador = 0;
        fila = 0;
    }

    void armarPanelTAb() {
        panelPest.setBackground(Color.WHITE);
        panelTab1.setBackground(Color.WHITE);
        panelTab2.setBackground(Color.WHITE);
        panelTab3.setBackground(Color.white);
        panelTab3.setLayout(null);
        panelTab1.setLayout(new GridLayout(2, 1));
        panelTab1.add(lblFecha,0);
        panelTab1.add(lblHora,1);
        scroll.setBounds(0, 0, 300, 180);
        panelTab3.add(scroll);
        btnEliminar.setBounds(330, 20, 100, 30);
        panelTab3.add(btnEliminar);
        btnActualizar.setBounds(330, 60, 100, 30);
        panelTab3.add(btnActualizar);
        panelPest.addTab("Fecha y Hora", null, panelTab1, "Primer panel");
        panelPest.addTab("Medidas", null, panelTab2, "Segundo panel");
        panelPest.addTab("Tabla de mensajes", null, panelTab3, "Tercer panel");
       
    }

    void armarPanelOeste() {
        panel.setLayout(null);
        scrollTextArea.setBounds(50, 125, 300, 178);
        btnAgregar.setBounds(150, 330, 100, 30);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date actualizard = new Date();
                if (texto.getText().length() <= 128 ){
                    Object [] registro={contador+1,texto.getText()};
                    dtm.addRow(registro);
                    lblFecha.setText("FECHA: " + actualizard.getDate()+ "/" + 
                            (actualizard.getMonth()+1) +
                             "/"+ (actualizard.getYear()+1900));
                    lblHora.setText("HORA: " + actualizard.getHours() + ":" + 
                            actualizard.getMinutes());
                    contador ++;
                }else{
                    JOptionPane.showMessageDialog(null,"Intenta mostrar un mensaje demasiado grande",
                                                  "Error",JOptionPane.ERROR_MESSAGE,null);
       
                }
                texto.setText(null);
            }
        });
        btnAgregar.setBackground(new Color(50, 141, 182));
        btnActualizar.setBackground(new Color(50, 141, 182));
        btnActualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    System.out.println(fila);
                    dtm.setValueAt(texto.getText(),fila,1);
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"No ha seleccionado "
                            + "ningun mensaje", "Error", 
                            JOptionPane.ERROR_MESSAGE,null);
                }
            }
            
        });
        tabla.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                texto.setText(dtm.getValueAt(tabla.getSelectedRow(), 1).toString());
                fila = tabla.getSelectedRow();
                System.out.println(fila);
            }
             
        });
        btnEliminar.setBackground(new Color(50, 141, 182));
        btnEliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               try{
                    dtm.removeRow(tabla.getSelectedRow());
                    
               }catch(Exception e){
                 JOptionPane.showMessageDialog(null,"No ha seleccionado "
                            + "ningun mensaje", "Error", 
                            JOptionPane.ERROR_MESSAGE,null);
                }
            }
            
        });
        btnAgregar.setForeground(Color.WHITE);
        btnEliminar.setForeground(Color.WHITE);
        btnActualizar.setForeground(Color.WHITE);
        panel.add(btnAgregar);
        panel.add(scrollTextArea);
        //panel.add(btnActualizar);
        //panel.add(btnEliminar);
    }

    void armarVentana() {
        panelSec.add(panelPest);
        panelSec.setBounds(400, 100, 450, 200);
        panel.setBounds(0, 0, 400, 550);
        this.setBackground(Color.WHITE);
        add(panelSec, BorderLayout.WEST);
        add(panel);
        //Falta panel para escribir los mensajes?
        //NO ya no, esperame poquito, si quieres yo lo acomodo y tu le pones funciones arre
    }

    void lanzar() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 550);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

