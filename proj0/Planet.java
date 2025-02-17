public class Planet{

    private static final double G=6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
                xxPos=xP;
                yyPos=yP;
                xxVel=xV;
                yyVel=yV;
                mass=m;
                imgFileName=img;
              }

    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet p){
        double tem=Math.pow(xxPos-p.xxPos,2)+Math.pow(yyPos-p.yyPos,2);
        return Math.sqrt(tem);
    }

    public double calcForceExertedBy(Planet p){
        return G*p.mass*mass/Math.pow(calcDistance(p),2);
    }

    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    }


    public double calcNetForceExertedByX(Planet[] p){
        double tem=0;
        for(Planet planet:p){
            if(planet.yyPos!=yyPos||planet.xxPos!=xxPos)
                tem+=calcForceExertedByX(planet);
        }
        return tem;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double tem=0;
        for(Planet planet:p){
            if(planet.yyPos!=yyPos||planet.xxPos!=xxPos)
                tem+=calcForceExertedByY(planet);
        }
        return tem;
    }

    public void update(double dt, double fX, double fY){
        xxVel+=fX* dt/ mass;
        yyVel+=fY* dt/ mass;
        xxPos+=xxVel* dt;
        yyPos+=yyVel* dt;
    }

    public void draw(){
        String tem="images/";
        tem+=imgFileName;
        StdDraw.picture(xxPos,yyPos,tem);
    }

}