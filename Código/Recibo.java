package impressora;

import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.OutputStream;

public class Recibo {

    public static void main(String[] args) {
        try {
            // Conexão com a impressora
            Socket impressora = new Socket("192.168.1.100", 9100);
            OutputStream saida = impressora.getOutputStream();

            // Inicialização e configuração da impressora
            saida.write(new byte[] {0x1B, 0x40});
            saida.write(new byte[] {0x1B, 0x74, 0x02});

            // Data e hora
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm");
            DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String dataHora = agora.format(formato) + "\n";
            String data = agora.format(formato2);

            // Cabeçalho do recibo
            saida.write(new byte[] {0x1B, 0x45, 0x01});
            saida.write(new byte[] {0x1B, 0x61, 0x01});
            saida.write(new byte[] {0x1D, 0x21, 0x11});

            saida.write("GRUPO GR\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x45, 0x00});
            saida.write(new byte[] {0x1D, 0x21, 0x00});

            saida.write("NOME DA RAZÃO SOCIAL COM. DE INFORMÁTICA LTDA-ME\n\n".getBytes("CP850"));

            saida.write("Rua dos Equipamentos, 9 - Sobreloja 101 e 102\n\n".getBytes("CP850"));

            saida.write("Centro - 20000-00 - Quillacollo/CBBA\n\n".getBytes("CP850"));

            saida.write("(11) 1111-2222 / 3333-4444\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x61, 0x00});
            saida.write("CNPJ: 11.111.111/0001-11          IE: 11.222.333\n\n".getBytes("CP850"));

            saida.write("CLIENTE: CONSUMIDOR FINAL\n\n".getBytes("CP850"));

            // Identificação da venda
            saida.write(new byte[] {0x1B, 0x45, 0x01});
            saida.write(new byte[] {0x1B, 0x61, 0x02});
            saida.write("COMPROVANTE DE VENDA\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x45, 0x00});
            saida.write(new byte[] {0x1B, 0x61, 0x00});
            saida.write(dataHora.getBytes("CP850"));

            saida.write(new byte[] {0x1D, 0x21, 0x11});
            saida.write(new byte[] {0x1B, 0x61, 0x02});
            saida.write("N°000001\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1D, 0x21, 0x00});
            saida.write(new byte[] {0x1B, 0x61, 0x01});
            saida.write("________________________________________________\n\n\n".getBytes("CP850"));

            // Produtos
            saida.write(new byte[] {0x1B, 0x61, 0x00});
            saida.write("CÓDIGO               DESCRIÇÃO          GARANTIA\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x61, 0x02});
            saida.write("QTD x UNIT         R$ VALOR\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x61, 0x01});
            saida.write("________________________________________________\n\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x61, 0x00});
            saida.write(new byte[] {0x1B, 0x45, 0x01});

            saida.write("05001  HD 500GB SEAGATE MOMENUS 7200.4        3M\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x61, 0x02});
            saida.write("1 x 250,00      250,00\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x61, 0x00});
            saida.write("05001 MEMÓRIA 4GB DDR3 133MHZ - MARKVISION    3M\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x61, 0x02});
            saida.write("1 x 70,00       70,00\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x61, 0x01});
            saida.write("________________________________________________\n\n\n".getBytes("CP850"));

            // Valores da venda
            saida.write(new byte[] {0x1B, 0x61, 0x00});
            saida.write("Total da nota R$                          320,00\n\n".getBytes("CP850"));

            saida.write("Valor recebido R$                         400,00\n\n".getBytes("CP850"));

            saida.write("Troco R$                                    80,00\n\n\n".getBytes("CP850"));

            // Forma de pagamento
            saida.write("FORMA DE PGTO.:".getBytes("CP850"));
            saida.write(new byte[] {0x1B, 0x45, 0x00});
            saida.write("À VISTA\n\n\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x45, 0x01});
            saida.write("DATA PGTO            R$ VALOR       TIPO PGTO\n".getBytes("CP850"));

            saida.write(new byte[] {0x1B, 0x45, 0x00});
            saida.write(data.getBytes("CP850"));
            saida.write("            320,00         DINHEIRO\n\n".getBytes("CP850"));

            // Vendedor e números de série
            saida.write("________________________________________________\n\n".getBytes("CP850"));

            saida.write("VENDEDOR(A): VENDEDOR 1\n".getBytes("CP850"));
            saida.write("________________________________________________\n\n\n\n".getBytes("CP850"));

            saida.write("Números de Série:\n".getBytes("CP850"));
            saida.write("HJGB458454FJH, YHRUEF785848347583\n\n\n\n".getBytes("CP850"));

            // Termo e assinatura do cliente
            saida.write(new byte[] {0x1B, 0x61, 0x01});

            saida.write("Recebi a(s) mercadoria(s) acima descrita(s), \n".getBytes("CP850"));

            saida.write("concordando plenamente com os prazos e condições de garantia.\n\n\n\n".getBytes("CP850"));

            saida.write(".........................................\n".getBytes("CP850"));

            saida.write("ASSINATURA DO CLIENTE\n\n".getBytes("CP850"));

            saida.write("________________________________________________\n\n".getBytes("CP850"));

            // Mensagem final
            saida.write(new byte[] {0x1B, 0x45, 0x01});
            saida.write("* OBRIGADO E VOLTE SEMPRE *\n\n".getBytes("CP850"));

            // Avanço e corte do papel
            saida.write(new byte[] {0x1B, 0x64, 0x05});
            saida.write(new byte[] {0x1D, 0x56, 0x00});

            // Envio dos dados e encerramento da conexão
            saida.flush();
            impressora.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
