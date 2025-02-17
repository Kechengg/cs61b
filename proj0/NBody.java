public class NBody{

    public static double readRadius(String fn){
        In in= new In(fn);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet [] readPlanets(String fn){
        Planet [] p=new Planet[5];
        In in= new In(fn);
        int fir=in.readInt();
        double sec=in.readDouble();
        for(int i=0;i<5;i++){
            double xp=in.readDouble();
            double yp=in.readDouble();
            double xv=in.readDouble();
            double yv=in.readDouble();
            double m=in.readDouble();
            String imgname=in.readString();
            p[i]=new Planet(xp,yp,xv,yv,m,imgname);
        }
        return p;
    }

    public static void main(String[] args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        Planet[] planets=readPlanets(filename);
        double radius=readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(Planet planet:planets ){
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();
        double time=0;
        for(;time<T;time+=dt){
            double[] xForces=new double[5];
            double[] yForces=new double[5];
            for(int i=0;i<5;i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0;i<5;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet planet:planets ){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}