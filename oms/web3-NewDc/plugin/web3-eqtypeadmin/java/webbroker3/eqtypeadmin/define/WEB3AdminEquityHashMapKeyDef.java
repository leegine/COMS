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
filename	WEB3AdminEquityHashMapKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : HashMapKey�萔��`�C���^�t�F�C�X(WEB3AdminEquityHashMapKeyDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/05 ����(���u) �V�K�쐬
Revision History : 2009/01/05 ����(���u) ���f�� No.237
Revision History : 2009/05/06 ���ʗ�(���u) ���f�� No.243 �c�a�X�V�d�lNo.032
*/
package webbroker3.eqtypeadmin.define;

/**
 * HashMapKey �萔��`�C���^�t�F�C�X
 *
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminEquityHashMapKeyDef
{
    /**
     * �����R�[�h : product_code
     */
    public final static String PRODUCT_CODE = "product_code";

    /**
     * �s��R�[�h : market_code
     */
    public final static String MARKET_CODE = "market_code";

    /**
     * ��񔭐����� : info_generation_timestamp
     */
    public final static String INFO_GENERATION_TIMESTAMP = "info_generation_timestamp";

    /**
     * ������~�����^�����ĊJ���� : trade_stop_restart_timestamp
     */
    public final static String TRADE_STOP_RESTART_TIMESTAMP = "trade_stop_restart_timestamp";

    /**
     * ������t�ĊJ���� : ord_receipt_restart_timestamp
     */
    public final static String ORD_RECEIPT_RESTART_TIMESTAMP = "ord_receipt_restart_timestamp";

    /**
     * ��l�i�ύX��j : new_base_price
     */
    public final static String NEW_BASE_PRICE= "new_base_price";

    /**
     * �����l������i�ύX��j : new_high_price_range
     */
    public final static String NEW_HIGH_PRICE_RANGE = "new_high_price_range";

    /**
     * �����l�������i�ύX��j : new_low_price_range
     */
    public final static String NEW_LOW_PRICE_RANGE = "new_low_price_range";

    /**
     * ��l�i�ύX�O�j : old_base_price
     */
    public final static String OLD_BASE_PRICE = "old_base_price";

    /**
     * �����l������i�ύX�O�j : old_high_price_range
     */
    public final static String OLD_HIGH_PRICE_RANGE = "old_high_price_range";

    /**
     * �����l�������i�ύX�O�j : old_low_price_range
     */
    public final static String OLD_LOW_PRICE_RANGE = "old_low_price_range";

    /**
     * �\�� : title
     */
    public final static String TITLE = "title";

    /**
     * �]���P���i�ύX�O�j : old_estimation_price
     */
    public final static String OLD_ESTIMATION_PRICE = "old_estimation_price";

    /**
     * �]���P���i�ύX��j : new_estimation_price
     */
    public final static String NEW_ESTIMATION_PRICE = "new_estimation_price";

    /**
     * ��l�i�I�l�j�i�ύX�O�j : old_last_closing_price
     */
    public final static String OLD_LAST_CLOSING_PRICE = "old_last_closing_price";

    /**
     * ��l�i�I�l�j�i�ύX��j : new_last_closing_price
     */
    public final static String NEW_LAST_CLOSING_PRICE = "new_last_closing_price";

    /**
     * �l���`�F�b�N�敪�i�ύX�O�j : old_price_range_type
     */
    public final static String OLD_PRICE_RANGE_TYPE = "old_price_range_type";

    /**
     * �l���`�F�b�N�敪(�ύX��) : new_price_range_type
     */
    public final static String NEW_PRICE_RANGE_TYPE = "new_price_range_type";

    /**
     * �l���敪�i�ύX�O�j : old_price_range_unit_type
     */
    public final static String OLD_PRICE_RANGE_UNIT_TYPE = "old_price_range_unit_type";

    /**
     * �l���敪�i�ύX��j : new_price_range_unit_type
     */
    public final static String NEW_PRICE_RANGE_UNIT_TYPE = "new_price_range_unit_type";

    /**
     * ��l�i�I�l�j�i�����j�i�ύX�O�j : old_last_closing_price_updq
     */
    public final static String OLD_LAST_CLOSING_PRICE_UPDQ = "old_last_closing_price_updq";

    /**
     * ��l�i�I�l�j�i�����j�i�ύX��j : new_last_closing_price_updq
     */
    public final static String NEW_LAST_CLOSING_PRICE_UPDQ = "new_last_closing_price_updq";

    /**
     * ��l�i�����j�i�ύX�O�j : old_base_price_updq
     */
    public final static String OLD_BASE_PRICE_UPDQ = "old_base_price_updq";

    /**
     * ��l�i�����j�i�ύX��j : new_base_price_updq
     */
    public final static String NEW_BASE_PRICE_UPDQ = "new_base_price_updq";
}@
