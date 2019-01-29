head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteRecordFormat.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteRecordFormat�N���X(WEB3QuoteRecordFormat.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/10 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.quoteadaptor.stdimpls;


/**
 * �����T�[�o����M���鎞�����R�[�h�̃t�H�[�}�b�g�Ɋւ���萔�N���X
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
interface WEB3QuoteRecordFormat
{

    /** �������t�̒��� */
    static final int QUOTE_DATE_SIZE = 8;

    /** ���A���敪�̒��� */
    static final int REAL_TYPE_SIZE = 1;

    /** ��ʋ敪�̒��� */
    static final int DATA_TYPE_SIZE = 1;

    /** �s��R�[�h�̒��� */
    static final int MARKET_CODE_SIZE = 2;

    /** �����R�[�h�̒��� */
    static final int PRODUCT_CODE_SIZE = 9;

    /** �����̒��� */
    static final int MONTH_OF_DELIVERY_SIZE = 6;

    /** �v�b�g���R�[���̒��� */
    static final int PUT_AND_CALL_SIZE = 1;

    /** �s�g���i�̒��� */
    static final int STRIKE_PRICE_SIZE = 12;

    /** �n�l�̒��� */
    static final int OPEN_PRICE_SIZE = 12;

    /** �n�l�����̒��� */
    static final int OPEN_PRICE_TIME_SIZE = 4;

    /** ���l�̒��� */
    static final int HIGH_PRICE_SIZE = 12;

    /** ���l�����̒��� */
    static final int HIGH_PRICE_TIME_SIZE = 4;

    /** ���l�̒��� */
    static final int LOW_PRICE_SIZE = 12;

    /** ���l�����̒��� */
    static final int LOW_PRICE_TIME_SIZE = 4;

    /** ���ݒl�̒��� */
    static final int CURRENT_PRICE_SIZE = 12;

    /** ���ݒl�����̒��� */
    static final int CURRENT_PRICE_TIME_SIZE = 4;

    /** ���ݒl�t���O�̒��� */
    static final int CURRENT_PRICE_FLAG_SIZE = 1;

    /** �O����̒��� */
    static final int CHANGE_SIZE = 12;

    /** �o�����̒��� */
    static final int VOLUME_SIZE = 12;

    /** �o���������̒��� */
    static final int VOLUME_TIME_SIZE = 4;

    /** ���C�z�l�^�C�g���̒��� */
    static final int ASK_PRICE_TITLE_SIZE = 1;

    /** ���C�z�l�̒��� */
    static final int ASK_PRICE_SIZE = 12;

    /** ���C�z�l�����̒��� */
    static final int ASK_PRICE_TIME_SIZE = 4;

    /** ���C�z�l�^�C�g���̒��� */
    static final int BID_PRICE_TITLE_SIZE = 1;

    /** ���C�z�l�̒��� */
    static final int BID_PRICE_SIZE = 12;

    /** ���C�z�l�����̒��� */
    static final int BID_PRICE_TIME_SIZE = 4;

    /** ��l�i�̒��� */
    static final int BASE_PRICE_SIZE = 12;

}
@
