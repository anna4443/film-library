package com.as;

import com.as.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReflectionExample {

    public static void main(String[] args) {
        AllInformations allInfo = new AllInformations(new Korisnik(), new Loan(), new Member(), new Movie(), new Overdue());
        Korisnik k = new Korisnik();
        Loan l = new Loan();
        Member mb = new Member();
        Movie m = new Movie();
        Overdue o = new Overdue();

        StringBuffer bafer = new StringBuffer();
        bafer.append("<!DOCTYPE html>\n");
        bafer.append("<html>\n");
        bafer.append("<head>\n");
        bafer.append("<title>Moja prva dokumentacija</title>\n");
        bafer.append("</head>\n");
        bafer.append("<body>\n");
        bafer.append("<h1>Popis klasa</h1>\n");

        try {
            FileWriter pisac = new FileWriter(new File("C:\\Users\\Ana\\Desktop\\dokumentacija.html"), false);

            Class klasa = allInfo.getClass();
            Class klasaKorisnik = k.getClass();
            Class klasaPosudba = l.getClass();
            Class klasaClan = mb.getClass();
            Class klasaFilm = m.getClass();
            Class klasaZakasnina = o.getClass();

            bafer.append("<h2>" + klasa.getName() +  "</h2>\n");
            bafer.append("<h2>" + klasaKorisnik.getName() +  "</h2>\n");
            bafer.append("<h2>" + klasaPosudba.getName() +  "</h2>\n");
            bafer.append("<h2>" + klasaClan.getName() +  "</h2>\n");
            bafer.append("<h2>" + klasaFilm.getName() +  "</h2>\n");
            bafer.append("<h2>" + klasaZakasnina.getName() +  "</h2>\n");

            bafer.append("<h3>Popis konstruktora</h3>\n");
            bafer.append("<table border='1'>\n");
            bafer.append("<th>Naziv konstruktora</th>"
                    + "<th>Parametri konstruktora</th>");

            for(Constructor c : klasa.getConstructors()) {
                bafer.append("<tr><td>\n");
                bafer.append(c.getName());
                bafer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td></tr>\n");
            }

            for(Constructor c : klasaKorisnik.getConstructors()) {
                bafer.append("<tr><td>\n");
                bafer.append(c.getName());
                bafer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td></tr>\n");
            }

            for(Constructor c : klasaPosudba.getConstructors()) {
                bafer.append("<tr><td>\n");
                bafer.append(c.getName());
                bafer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td></tr>\n");
            }

            for(Constructor c : klasaClan.getConstructors()) {
                bafer.append("<tr><td>\n");
                bafer.append(c.getName());
                bafer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td></tr>\n");
            }

            for(Constructor c : klasaFilm.getConstructors()) {
                bafer.append("<tr><td>\n");
                bafer.append(c.getName());
                bafer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td></tr>\n");
            }

            for(Constructor c : klasaZakasnina.getConstructors()) {
                bafer.append("<tr><td>\n");
                bafer.append(c.getName());
                bafer.append("</td><td>\n");
                if(c.getParameterCount() > 0) {
                    for(Parameter parameter : c.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td></tr>\n");
            }

            bafer.append("</table>\n");

            bafer.append("<h3>Popis metoda</h3>\n");
            bafer.append("<table border='1'>\n");
            bafer.append("<th>Naziv metode</th>"
                    + "<th>Ulazni parametri metode</th>"
                    + "<th>Izlazni parametar metode</th>");

            for(Method metoda : klasa.getMethods()) {
                bafer.append("<tr><td>\n");
                bafer.append(metoda.getName());
                bafer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }

                bafer.append("</td><td>\n");
                bafer.append(metoda.getReturnType().getName() + "</td>");
            }

            for(Method metoda : klasaKorisnik.getMethods()) {
                bafer.append("<tr><td>\n");
                bafer.append(metoda.getName());
                bafer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td><td>\n");
                bafer.append(metoda.getReturnType().getName() + "</td>");
            }

            for(Method metoda : klasaPosudba.getMethods()) {
                bafer.append("<tr><td>\n");
                bafer.append(metoda.getName());
                bafer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td><td>\n");
                bafer.append(metoda.getReturnType().getName() + "</td>");
            }

            for(Method metoda : klasaClan.getMethods()) {
                bafer.append("<tr><td>\n");
                bafer.append(metoda.getName());
                bafer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td><td>\n");
                bafer.append(metoda.getReturnType().getName() + "</td>");
            }

            for(Method metoda : klasaFilm.getMethods()) {
                bafer.append("<tr><td>\n");
                bafer.append(metoda.getName());
                bafer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td><td>\n");
                bafer.append(metoda.getReturnType().getName() + "</td>");
            }

            for(Method metoda : klasaZakasnina.getMethods()) {
                bafer.append("<tr><td>\n");
                bafer.append(metoda.getName());
                bafer.append("</td><td>\n");
                if(metoda.getParameterCount() > 0) {
                    for(Parameter parameter : metoda.getParameters()) {
                        bafer.append(parameter.getType().getName() + " "
                                + parameter.getName() + "\n");

                    }
                }
                bafer.append("</td><td>\n");
                bafer.append(metoda.getReturnType().getName() + "</td>");
            }

            bafer.append("</table>\n");

            for(Field polje : klasa.getDeclaredFields()) {
                String nazivVarijable = polje.getName();
                System.out.println("Naziv varijable je:" + nazivVarijable);
                System.out.println("Access modifier je:" + polje.getModifiers());
            }

            bafer.append("<h3>Popis varijabli</h3>\n");
            bafer.append("<table border='1'>\n");
            bafer.append("<th>Naziv varijable</th>");

            for(Field polje : klasa.getDeclaredFields()) {
                bafer.append("<tr><td>\n");
                bafer.append(polje.getName());
                bafer.append("</td></tr>\n");
            }

            for(Field polje : klasaKorisnik.getDeclaredFields()) {
                bafer.append("<tr><td>\n");
                bafer.append(polje.getName());
                bafer.append("</td></tr>\n");
            }

            for(Field polje : klasaPosudba.getDeclaredFields()) {
                bafer.append("<tr><td>\n");
                bafer.append(polje.getName());
                bafer.append("</td></tr>\n");
            }

            for(Field polje : klasaClan.getDeclaredFields()) {
                bafer.append("<tr><td>\n");
                bafer.append(polje.getName());
                bafer.append("</td></tr>\n");
            }

            for(Field polje : klasaFilm.getDeclaredFields()) {
                bafer.append("<tr><td>\n");
                bafer.append(polje.getName());
                bafer.append("</td></tr>\n");
            }

            for(Field polje : klasaZakasnina.getDeclaredFields()) {
                bafer.append("<tr><td>\n");
                bafer.append(polje.getName());
                bafer.append("</td></tr>\n");
            }

            bafer.append("</body>\n");
            bafer.append("</html>\n");

            pisac.append(bafer.toString());

            pisac.flush();
            pisac.close();

        } catch (IOException ex) {
            Logger.getLogger(ReflectionExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
