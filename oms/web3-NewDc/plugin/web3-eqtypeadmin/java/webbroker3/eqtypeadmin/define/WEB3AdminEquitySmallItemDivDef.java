head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquitySmallItemDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ڋ敪 �萔��`�C���^�t�F�C�X(WEB3AdminEquitySmallItemDivDef.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.define;

/**
 * �����ڋ敪 �萔��`�C���^�t�F�C�X
 *
 * @@author Babu Prasad
 * @@version 1.0
 */
public interface WEB3AdminEquitySmallItemDivDef
{
    /**
     * ������������~
     */
    public final static String TRADE_STOP = "0";

    /**
     * ���x�M�p���������~
     */
    public final static String MARGIN_SYS_TRADE_STOP = "1";

    /**
     * ��ʐM�p���������~
     */
    public final static String MARGIN_GEN_TRADE_STOP = "2";

    /**
     * ��������~
     */
    public final static String BUY_CASH_STOP = "3";

    /**
     * ��������~
     */
    public final static String SELL_CASH_STOP = "4";

    /**
     * �����x�M�p��~
     */
    public final static String LONG_MARGIN_SYS_STOP = "5";

    /**
     * �����x�M�p��~
     */
    public final static String SHORT_MARGIN_SYS_STOP = "6";

    /**
     * �����ԍϐ��x�M�p��~
     */
    public final static String LONG_CLOSE_MARGIN_SYS_STOP = "7";

    /**
     * �����ԍϐ��x�M�p��~
     */
    public final static String SHORT_CLOSE_MARGIN_SYS_STOP = "8";

    /**
     * �������x�M�p��~
     */
    public final static String LONG_SWAP_MARGIN_SYS_STOP = "9";

    /**
     * ���n���x�M�p��~
     */
    public final static String SHORT_SWAP_MARGIN_SYS_STOP = "10";

    /**
     * ����ʐM�p��~
     */
    public final static String LONG_MARGIN_GEN_STOP = "11";

    /**
     * ����ʐM�p��~
     */
    public final static String SHORT_MARGIN_GEN_STOP = "12";

    /**
     * �����ԍψ�ʐM�p��~
     */
    public final static String LONG_CLOSE_MARGIN_GEN_STOP = "13";

    /**
     *�����ԍψ�ʐM�p��~
     */
    public final static String SHORT_CLOSE_MARGIN_GEN_STOP = "14";

    /**
     * ������ʐM�p��~
     */
    public final static String LONG_SWAP_MARGIN_GEN_STOP = "15";

    /**
     * ���n��ʐM�p��~
     */
    public final static String SHORT_SWAP_MARGIN_GEN_STOP = "16";

    /**
     * ��������~(���s)
     */
    public final static String BUY_SPOT_MARKET_ORD_DES_STOP = "17";

    /**
     * ��������~(���s)
     */
    public final static String SELL_SPOT_MARKET_ORD_DES_STOP = "18";

    /**
     * �����x�M�p��~(���s)
     */
    public final static String LONG_MS_MARKET_ORD_DES_STOP = "19";

    /**
     * �����x�M�p��~(���s)
     */
    public final static String SHORT_MS_MARKET_ORD_DES_STOP = "20";

    /**
     * �����ԍϐ��x�M�p��~(���s)
     */
    public final static String LONG_CMS_MARKET_ORD_DES_STOP = "21";

    /**
     * �����ԍϐ��x�M�p��~(���s)
     */
    public final static String SHORT_CMS_MARKET_ORD_DES_STOP = "22";

    /**
     * ����ʐM�p��~(���s)
     */
    public final static String LONG_MG_MARKET_ORD_DES_STOP = "23";

    /**
     * ����ʐM�p��~(���s)
     */
    public final static String SHORT_MG_MARKET_ORD_DES_STOP = "24";

    /**
     * �����ԍψ�ʐM�p��~(���s)
     */
    public final static String LONG_CMG_MARKET_ORD_DES_STOP = "25";

    /**
     * �����ԍψ�ʐM�p��~(���s)
     */
    public final static String SHORT_CMG_MARKET_ORD_DES_STOP = "26";

    /**
     * ���~�j����~
     */
    public final static String BUY_MINI_STOCK_STOP = "27";

    /**
     * ���~�j����~
     */
    public final static String SELL_MINI_STOCK_STOP = "28";

    /**
     * ������
     */
    public final static String STANDARD_NAME = "29";

    /**
     * �����P��
     */
    public final static String LOT_SIZE = "30";

    /**
     * �������x�P��
     */
    public final static String COMPULSIVE_LIMITED_UNIT = "31";

    /**
     * �������
     */
    public final static String OPEN_OTC_DIV = "32";

    /**
     * ���敪
     */
    public final static String LIST_TYPE = "33";

    /**
     * ���������K��
     */
    public final static String TODAY_DEP_FUND_REG = "34";

    /**
     * �����~�j�����s��
     */
    public final static String MINI_STOCK_MARKET = "35";

    /**
     * ���~�j����������
     */
    public final static String BUY_MINI_STOCK_TIME_LIMIT = "36";

    /**
     * ���~�j����������
     */
    public final static String SELL_MINI_STOCK_TIME_LIMIT = "37";

    /**
     * ���x�M�p�����敪
     */
    public final static String MARGIN_SYS_PRODUCT_TYPE = "38";

    /**
     * ��ʐM�p�����敪
     */
    public final static String MARGIN_GEN_PRODUCT_TYPE = "39";

    /**
     * ���ۏ؋���
     */
    public final static String LONG_MARGIN_DEPOSIT_RATE = "40";

    /**
     * ���ۏ؋���
     */
    public final static String SHORT_MARGIN_DEPOSIT_RATE = "41";

    /**
     * �������ۏ؋���
     */
    public final static String LONG_CASH_MARGIN_DEPOSIT_RATE = "42";

    /**
     * �������ۏ؋���
     */
    public final static String SHORT_CASH_MARGIN_DEPOSIT_RATE = "43";

    /**
     * ��p�|��
     */
    public final static String MARGIN_RATIO = "44";

    /**
     * ��p�]���P��
     */
    public final static String ESTIMATION_PRICE = "45";

    /**
     * �a��،��]���|��
     */
    public final static String SECURITIES_ESTIMATION_RATIO = "46";

    /**
     * ��l(�I�l)
     */
    public final static String LAST_CLOSING_PRICE = "47";

    /**
     * �l���`�F�b�N
     */
    public final static String PRICE_RANGE_TYPE = "48";

    /**
     * �l��
     */
    public final static String PRICE_RANGE = "49";

    /**
     * �����l��
     */
    public final static String COMPULSIVE_PRICE_RANGE = "50";

    /**
     * �����l��(�l���敪)
     */
    public final static String PRICE_RANGE_UNIT_TYPE = "51";

    /**
     * �����l��(����)
     */
    public final static String LOW_COMPULSIVE_PRICE_RANGE = "52";

    /**
     * �����l��(���)
     */
    public final static String HIGH_COMPULSIVE_PRICE_RANGE = "53";

	/**
	 * �D��s��
	 */
	public final static String PRIMARY_MARKET = "54";

	/**
	 * ��������戵
	 */
    public final static String CAPITAL_GAIN_TAX_DEALING = "55";

    /**
     * ��l
     */
    public final static String BASE_PRICE = "56";
}
@
