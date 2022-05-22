import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

        Biblioteca biblioteca = new Biblioteca();

        boolean finalizar = false;

        do {
            Main.desenhaMenu(biblioteca.getListaArtista().size());

            String nomeArtista, nomeAlbum, nomeMusica;

            Date dataLancamento;

            int opcaoMenu, indiceArtista, indiceAlbum, indiceMusica;

            float duracaoMusica = 0;
            opcaoMenu = Integer.parseInt(leitor.readLine());

            switch (opcaoMenu) {
                // Cadastra um artista
                case 1:
                    System.out.print("Digite o nome do novo artista: ");
                    nomeArtista = leitor.readLine();

                    biblioteca.addArtista(nomeArtista);

                    System.out.println("Artista adicionado com sucesso!");
                    break;
                // Cadastra um álbum
                case 2:
                    try {
                        System.out.print("Digite o nome do artista: ");
                        nomeArtista = leitor.readLine();

                        indiceArtista = biblioteca.buscaArtista(nomeArtista);

                        if (indiceArtista >= 0) {
                            System.out.print("Digite o nome do novo álbum: ");
                            nomeAlbum = leitor.readLine();

                            System.out.print("Digite a data de lançamento do álbum (dd/mm/yyyy): ");
                            dataLancamento = new SimpleDateFormat("dd/MM/yyyy").parse(leitor.readLine());

                            biblioteca.addAlbum(indiceArtista, nomeAlbum, dataLancamento);
                            System.out.println("Álbum cadastrado com sucesso!");
                        } else {
                            System.out.println("Não foi possível cadastrar o álbum: artista não encontrado.");
                        }
                    } catch (ParseException e) {
                        System.out.println("Não foi possível cadastrar o álbum: formato da data é inválido.");
                    } catch (Exception e) {
                        System.out.println("Não foi possível cadastrar o álbum, tente novamente.");
                    }
                    break;
                // Cadastra uma música
                case 3:
                    System.out.print("Digite o nome do artista: ");
                    nomeArtista = leitor.readLine();

                    indiceArtista = biblioteca.buscaArtista(nomeArtista);

                    if (indiceArtista >= 0) {
                        System.out.print("Digite o nome do novo álbum: ");
                        nomeAlbum = leitor.readLine();

                        indiceAlbum = biblioteca.buscaAlbum(nomeAlbum, indiceArtista);

                        if (indiceAlbum >= 0) {
                            System.out.print("Digite o nome da música: ");
                            nomeMusica = leitor.readLine();

                            System.out.print("Digite a duração da música (em segundos): ");
                            duracaoMusica = Float.parseFloat(leitor.readLine());

                            biblioteca.addMusica(indiceArtista, indiceAlbum, nomeMusica, duracaoMusica);

                            System.out.println("Música adicionada com sucesso!");
                        } else {
                            System.out.println("Não foi possível cadastrar a música: álbum não encontrado.");
                        }
                    } else {
                        System.out.println("Não foi possível cadastrar a música: artista não encontrado.");
                    }
                    break;
                // Visualiza o artista
                case 4:
                    System.out.print("Digite o nome do artista: ");
                    nomeArtista = leitor.readLine();

                    indiceArtista = biblioteca.buscaArtista(nomeArtista);

                    if (indiceArtista >= 0) {
                        System.out.print(biblioteca.getListaArtista().get(indiceArtista));
                    } else {
                        System.out.println("Artista não encontrado.");
                    }

                    break;
                // Apaga lista
                case 5:
                    biblioteca.setListaArtista(new ArrayList<Artista>());
                    System.out.println("Lista de artistas apagada.");
                    break;
                // Lista todos os artistas, álbums e músicas
                case 6:
                    System.out.print(biblioteca);
                    break;
                case 0:
                    finalizar = true;

                    break;
                default:
                    System.out.println("Opção inválida, digite novamente.");

                    break;
            }
        } while (!finalizar);
    }

    /**
     * Desenha o menu e as opções disponíveis para seleção
     *
     * @param qtdeArtistas informa a quantidade de artistas total
     */
    public static void desenhaMenu(int qtdeArtistas) {
        System.out.println("----------- Artistas Musicais ----------");
        System.out.println("Você possui " + qtdeArtistas + " artistas");
        System.out.println("Opções disponíveis: \n1. Cadastrar artista\n2. Adicionar álbum\n3. Adicionar música\n4. Visualizar artista\n5. Apagar todos os artistas\n6. Visualizar artistas e seus álbuns\n0. Encerrar programa");
        System.out.print("Selecione a opção: ");
    }
}
