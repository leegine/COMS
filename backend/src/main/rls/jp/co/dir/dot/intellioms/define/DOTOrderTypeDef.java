/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OrderTypeDefクラス(DOTOrderTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/01 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.define;


/**
 * 
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTOrderTypeDef
{

    public static final int UNDEFINED = 0;
    public static final int EQUITY_BUY = 1;
    public static final int EQUITY_SELL = 2;
    public static final int MARGIN_LONG = 3;
    public static final int MARGIN_SHORT = 4;
    public static final int CLOSE_MARGIN_LONG = 5;
    public static final int CLOSE_MARGIN_SHORT = 6;
    public static final int SWAP_MARGIN_LONG = 7;
    public static final int SWAP_MARGIN_SHORT = 8;
    public static final int IDX_FUTURES_BUY_TO_OPEN  = 601;
    public static final int IDX_FUTURES_SELL_TO_OPEN  = 602;
    public static final int IDX_FUTURES_BUY_TO_CLOSE  = 603;
    public static final int IDX_FUTURES_SELL_TO_CLOSE  = 604;
    public static final int IDX_OPTIONS_BUY_TO_OPEN  = 605;
    public static final int IDX_OPTIONS_SELL_TO_OPEN  = 606;
    public static final int IDX_OPTIONS_BUY_TO_CLOSE  = 607;
    public static final int IDX_OPTIONS_SELL_TO_CLOSE  = 608;
    
}
