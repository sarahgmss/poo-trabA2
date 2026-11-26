import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class frmLanchonete extends JFrame {
    
    private JLabel lblTituloNome, lblTituloCusto, lblTituloVenda, lblResultado, lblStatus;
    private JTextField txtNome, txtCusto, txtVenda;
    private JButton btnCalcular, btnConsultar, btnAlterar, btnExcluir;
    
    ArrayList<Produto> bancoDadosProdutos = new ArrayList<>();
    ArrayList<Categoria> bancoDadosCategorias = new ArrayList<>();

    public frmLanchonete() {
        setTitle("Sistema de Lanchonete - Gestão de Produtos");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome do Produto:");
        lblNome.setBounds(30, 30, 120, 25);
        add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(150, 30, 200, 25);
        add(txtNome);

        JLabel lblCusto = new JLabel("Preço de Custo (R$):");
        lblCusto.setBounds(30, 70, 120, 25);
        add(lblCusto);
        
        txtCusto = new JTextField();
        txtCusto.setBounds(150, 70, 200, 25);
        add(txtCusto);

        JLabel lblVenda = new JLabel("Preço de Venda (R$):");
        lblVenda.setBounds(30, 110, 120, 25);
        add(lblVenda);
        
        txtVenda = new JTextField();
        txtVenda.setBounds(150, 110, 200, 25);
        add(txtVenda);

        btnCalcular = new JButton("Calcular Lucro");
        btnCalcular.setBounds(30, 160, 130, 30);
        add(btnCalcular);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(170, 160, 100, 30);
        add(btnConsultar);

        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(280, 160, 80, 30);
        add(btnAlterar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(370, 160, 70, 30);
        add(btnExcluir);

        lblResultado = new JLabel("Lucro do produto: ");
        lblResultado.setBounds(30, 220, 400, 25);
        add(lblResultado);

        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(30, 250, 400, 25);
        add(lblStatus);

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText();
                    double custo = Double.parseDouble(txtCusto.getText());
                    double venda = Double.parseDouble(txtVenda.getText());
                    
                    Produto p = new Produto(nome, custo, venda);
                    
                    lblResultado.setText("O lucro do produto " + p.getNome() + " é: R$ " + p.getLucro());
                    
                    if (p.getStatus().equals("Margem Baixa")) {
                        lblStatus.setText(p.getNome() + " está com " + p.getStatus());
                        lblStatus.setForeground(new java.awt.Color(255, 0, 0));
                    } else {
                        lblStatus.setText(p.getNome() + " está com " + p.getStatus());
                        lblStatus.setForeground(new java.awt.Color(0, 0, 255));
                    }
                    
                    bancoDadosProdutos.add(p);
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    limparCampos();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Digite apenas números nos preços!");
                }
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeBusca = txtNome.getText();
                boolean achou = false;
                
                for (Produto p : bancoDadosProdutos) {
                    if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                        txtCusto.setText(String.valueOf(p.getPrecoCusto()));
                        txtVenda.setText(String.valueOf(p.getPrecoVenda()));
                        lblResultado.setText("Lucro do produto: R$ " + p.getLucro());
                        lblStatus.setText(p.getStatus());
                        achou = true;
                        break;
                    }
                }
                if (!achou) {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                }
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeBusca = txtNome.getText();
                
                for (Produto p : bancoDadosProdutos) {
                    if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                        p.setPrecoCusto(Double.parseDouble(txtCusto.getText()));
                        p.setPrecoVenda(Double.parseDouble(txtVenda.getText()));
                        
                        lblResultado.setText("Dados alterados! Novo lucro: R$ " + p.getLucro());
                        lblStatus.setText(p.getStatus());
                        JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Produto não encontrado para alterar.");
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeBusca = txtNome.getText();
                
                for (int i = 0; i < bancoDadosProdutos.size(); i++) {
                    if (bancoDadosProdutos.get(i).getNome().equalsIgnoreCase(nomeBusca)) {
                        bancoDadosProdutos.remove(i);
                        JOptionPane.showMessageDialog(null, "Produto excluído da tabela!");
                        limparCampos();
                        lblResultado.setText("Lucro do produto: ");
                        lblStatus.setText("Status:");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Produto não encontrado para excluir.");
            }
        });
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCusto.setText("");
        txtVenda.setText("");
        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmLanchonete().setVisible(true);
            }
        });
    }
}