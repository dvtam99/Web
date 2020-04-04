/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;




/**
 *
 * @author Tamdv

public class FilmDAO extends DBContext {

    public ArrayList<Film> getAllFilms() {
        ArrayList<Film> films = new ArrayList<>();

        try {
            String sql = "SELECT f.*,a.namea, a.ida,d.idd,d.named,fd.type, fd.reward, fd.country FROM\n"
                    + " PROJECTPRJ.dbo.film f JOIN PROJECTPRJ.dbo.Filmdetail fd ON fd.idf = f.idf\n"
                    + "  JOIN PROJECTPRJ.dbo.Actor a  ON fd.actorid = a.ida \n"
                    + "  JOIN PROJECTPRJ.dbo.Director d ON d.idd = fd.directorid";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            Film temp = new Film();
            temp.setIdf("241241824912412");

            while (rs.next()) {
                if (!rs.getString("idf").equals(temp.getIdf())) {

                    temp = new Film();
                    temp.setIdf(rs.getString("idf"));
                    temp.setName(rs.getString("name"));
                    temp.setTrailer(rs.getString("trailer"));
                    temp.setContentf(rs.getString("contentf"));
                    temp.setTime(rs.getString("time"));
                    temp.setLenght(rs.getString("length"));
                    temp.setLanguage(rs.getString("language"));
                    temp.setRating(rs.getFloat("rating"));
                    //System.out.println("sql"+rs.getString("ida")+rs.getString("namea"));
                    temp.listactor = new ArrayList<>();
                    temp.listdirector = new ArrayList<>();
                    temp.listtype = new ArrayList<>();
                    temp.listreward = new ArrayList<>();
                    temp.listcountry = new ArrayList<>();

                    films.add(temp);
                }

                Actor a = new Actor(rs.getString("ida"), rs.getString("namea"));
                temp.listactor.add(a);

                Director d = new Director();
                d.setIdd(rs.getString("idd"));
                d.setName(rs.getString("named"));
                temp.addDirector(d);

                Type t = new Type();
                t.setType(rs.getString("type"));
                temp.addType(t);

                Reward r = new Reward();
                r.setReward(rs.getString("reward"));
                temp.addReward(r);
                Country c = new Country();
                c.setCountry(rs.getString("country"));
                temp.addCountry(c);
            }

        } catch (Exception ex) {
            System.out.println("--------" + ex.getMessage());
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return films;
    }

    public Film getFilmsByID(String idf) {
        Film film = null;
        try {
            String sql = " SELECT f.*,a.namea, a.ida,d.idd,d.named,fd.type, fd.reward, fd.country FROM\n"
                    + " PROJECTPRJ.dbo.film f JOIN PROJECTPRJ.dbo.Filmdetail fd ON fd.idf = f.idf\n"
                    + "  JOIN PROJECTPRJ.dbo.Actor a  ON fd.actorid = a.ida \n"
                    + "  JOIN PROJECTPRJ.dbo.Director d ON d.idd = fd.directorid\n"
                    + " WHERE fd.idf= ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idf);
            ResultSet rs = statement.executeQuery();

            Film temp = new Film();
            temp.setIdf("241241824912412");

            while (rs.next()) {
                if (!rs.getString("idf").equals(temp.getIdf())) {

                    idf = rs.getString("idf");
                    String name = (rs.getString("name"));
                    String trailer = (rs.getString("trailer"));
                    String contentf = (rs.getString("contentf"));
                    String time = (rs.getString("time"));
                    String lenght = (rs.getString("length"));
                    String language = (rs.getString("language"));
                    float rating = (rs.getFloat("rating"));
                    //System.out.println("sql"+rs.getString("ida")+rs.getString("namea"));
                    temp.listactor = new ArrayList<>();
                    temp.listdirector = new ArrayList<>();
                    temp.listtype = new ArrayList<>();
                    temp.listreward = new ArrayList<>();
                    temp.listcountry = new ArrayList<>();

                    film = new Film(idf, name, trailer, contentf, time, lenght, language, rating, temp.listactor, temp.listdirector, temp.listcountry, temp.listtype, temp.listreward);
                }

                Actor a = new Actor();

                a.setIda(rs.getString("ida"));
                a.setName(rs.getString("namea"));
                temp.listactor.add(a);
                // film.addActor(a);

//                    a.setIda("none");
//                    a.setName("none");
//                }
//                temp.addActor(a);
                //Actor a = new Actor(rs.getString("ida"), rs.getString("namea"));
                //temp.listactor.add(a); 
                Director d = new Director();
                d.setIdd(rs.getString("idd"));
                d.setName(rs.getString("named"));
                temp.addDirector(d);

                Type t = new Type();
                t.setType(rs.getString("type"));
                temp.addType(t);

                Reward r = new Reward();
                r.setReward(rs.getString("reward"));
                temp.addReward(r);
                Country c = new Country();
                c.setCountry(rs.getString("country"));
                temp.addCountry(c);

            }

        } catch (Exception ex) {
            System.out.println("--------" + ex.getMessage());
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return film;
    }

    public void insertFilmdetail(String idf, String type, String ida, String idd, String reward, String country) {
        // ArrayList<Review> reviews = new ArrayList<>();
        try {
            String sql = "INSERT INTO [PROJECTPRJ].[dbo].[Filmdetail]\n"
                    + "           ([idf]\n"
                    + "           ,[type]\n"
                    + "           ,[actorid]\n"
                    + "           ,[directorid]\n"
                    + "           ,[reward]\n"
                    + "           ,[country])\n"
                    + "     VALUES  (?,?, ?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, idf);
            statement.setString(2, type);
            statement.setString(3, ida);
            statement.setString(4, idd);
            statement.setString(5, reward);
            statement.setString(6, country);
            statement.executeUpdate();

        } catch (Exception ex) {
            System.out.println("------insertFilmdetail--" + ex.getMessage());
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertFilm(String idf, String name, String trailer, String contentf, String time, String length, String language) {
        // ArrayList<Review> reviews = new ArrayList<>();
        try {
            String sql = "INSERT INTO PROJECTPRJ.[dbo].[film]\n"
                    + "           ([idf]\n"
                    + "           ,[name]\n"
                    + "           ,[trailer]\n"
                    + "           ,[contentf]\n"
                    + "           ,[time]\n"
                    + "           ,[length]\n"
                    + "           ,[language]\n"
                    + "           ,[rating])\n"
                    + "     VALUES  (?,?, ?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, idf);
            statement.setString(2, name);
            statement.setString(3, trailer);
            statement.setString(4, contentf);
            statement.setString(5, time);
            statement.setString(6, length);
            statement.setString(7, language);
             statement.setFloat(8, 0);
            statement.executeUpdate();

        } catch (Exception ex) {
            System.out.println("------insertFilm--" + ex.getMessage());
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
/*

*/
