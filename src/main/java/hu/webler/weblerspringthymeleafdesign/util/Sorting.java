package hu.webler.weblerspringthymeleafdesign.util;

import java.text.ParseException;
import java.text.RuleBasedCollator;

public class Sorting {

    public static RuleBasedCollator createHungarianCollator() {
        String hungarianRules = "< a,A < á,Á < b,B < c,C < cs,Cs,CS < d,D < dz,Dz,DZ < dzs,Dzs,DZS < e,E < é,É < f,F < " +
                "g,G < gy,Gy,GY < h,H < i,I < í,Í < j,J < k,K < l,L < ly,Ly,LY < m,M < n,N < ny,Ny,NY < o,O < ó,Ó < ö,Ö < " +
                "ő,Ő < p,P < q,Q < r,R < s,S < sz,Sz,SZ < t,T < ty,Ty,TY < u,U < ú,Ú < ü,Ü < ű,Ű < v,V < w,W < x,X < y,Y < " +
                "z,Z < zs,Zs,ZS";

        try {
            return new RuleBasedCollator(hungarianRules);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the exception appropriately (throw it, log it, etc.)
            return null;
        }
    }

    private Sorting() {
    }
}
