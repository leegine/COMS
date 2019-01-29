head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteProductCodes.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : �����R�[�h���`�����N���X(WEB3QuoteConstants2.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/03 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.quoteadaptor.stdimpls;

import webbroker3.quoteadaptor.IndexType;

/**
 * �����T�[�o�[���瑗���Ă�������R�[�h���`�����N���X
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
final class WEB3QuoteProductCodes
{
    /**
     * �����i�ڔ����j
     */
    static final String EQUITY_SUFFIX = "0000";

    /**
     * ���o���ϊ����i�w���j
     */
    static final String NIKKEI225_INDEX = "0018";

    /**
     * TOPIX�i�w���j
     */
    static final String TOPIX = "0005";

    /**
     * ���o300�i�w���j
     */
    static final String NIKKEI300_INDEX = "0016";

    /**
     * �X�������w��
     */
    static final String JASDAQ_INDEX = "9004";

    /**
     * ����2���w��
     */
    static final String TSE_2ND_SECTION_INDEX = "9005";

    /**
     * ���̎����p�����R�[�h���A
     * �Ɩ����W�b�N�̖����R�[�h�̃t�H�[�}�b�g�ɕϊ�����B
     * 
     * @@param productCode �����p�̖����R�[�h
     * @@return �Ɩ����W�b�N��̖����R�[�h
     */
    static final String toEquityProductCode(String productCode)
    {
        if (productCode.length() == 9 && productCode.endsWith(EQUITY_SUFFIX))
        {
            return productCode.substring(0, 5);
        } else
        {
            StringBuffer message = new StringBuffer();
            message.append("Equity ProductCode [");
            message.append(productCode).append("]");
            message.append(" was not defined.");
            throw new IllegalArgumentException(message.toString());
        }
    }

    /**
     * �w����IndexType���玞�����̖����R�[�h�ɕϊ�����B
     * 
     * @@param indexType �w�����
     * @@return �������̖����R�[�h
     */
    static final String toIndexProductCode(IndexType indexType)
    {
        // TODO �����R�[�h�����܂�����IndexType�Ɉړ�����
        switch (indexType.intValue())
        {
            case IndexType.IntValues.NIKKEI225_INDEX :
                return NIKKEI225_INDEX;
            case IndexType.IntValues.TOPIX :
                return TOPIX;
            case IndexType.IntValues.NIKKEI300_INDEX :
                return NIKKEI300_INDEX;
            case IndexType.IntValues.JASDAQ_INDEX :
                return JASDAQ_INDEX;
            case IndexType.IntValues.TSE_2ND_SECTION_INDEX :
                return TSE_2ND_SECTION_INDEX;
            default :
                StringBuffer message = new StringBuffer();
                message.append("IndexType[").append(indexType).append("]");
                message.append(
                    " is unknown IndexType in WEB3QuoteDataSupplierService.");
                throw new IllegalArgumentException(message.toString());
        }
    }

}
@
