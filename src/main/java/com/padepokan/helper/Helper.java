package com.padepokan.helper;

import com.padepokan.constant.CommonConstant;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reza.mr
 */
public class Helper {

    public static int getPointByTransaction(BigDecimal amount) {

        double point = (amount.divide(new BigDecimal(2_000))).doubleValue();

        if (amount.compareTo(new BigDecimal(100_000)) > 0) {
            return (int) (point * 2);
        }

        if (amount.compareTo(new BigDecimal(100_000)) > 0) {
            return (int) (point * 1);
        }

        return 0;

    }

    public static long calcPoint(double amount) {
        double sisa = 0;
        double point = 0;
        final double KELIPATAN = 2000;

        if (amount >= 50_000) {
            sisa = amount - 50_000;
        } else {
            sisa = amount;
        }

        // 50_001 => amount <= 10000
        if (sisa > 0 && sisa > 100_000) {
            sisa -= 100_000;
            point += (100_000 / KELIPATAN) * 1;
        } else if (sisa > 0 && sisa + 1 >= 50_001) {
            sisa -= 50_000;
            point += (50_000 / KELIPATAN) * 1;
        } else {
            point += (sisa / KELIPATAN) * 1;
        }

        // amount > 100_000
        if (sisa > 0 && sisa > 100_000) {
            point += (sisa / KELIPATAN) * 2;
        } else {
            point += (sisa / KELIPATAN) * 2;
        }

        return (long) point;
    }
    
    public static boolean isGainPoint(String transactionType) {
        List<String> transactionTypeGainPoint = new ArrayList<String>() {
            {
                add(CommonConstant.BAYAR_LISTRIK.toLowerCase());
                add(CommonConstant.BELI_PULSA.toLowerCase());
            }
        };

        return transactionTypeGainPoint.contains(transactionType.toLowerCase());
    }
    
}
