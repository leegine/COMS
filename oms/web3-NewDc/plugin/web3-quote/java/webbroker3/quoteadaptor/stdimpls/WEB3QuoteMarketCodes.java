head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteMarketCodes.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p�̎s��R�[�g�̒�`�N���X(WEB3QuoteMarketCodes.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/21 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.quoteadaptor.stdimpls;

/**
 * �����p�̎s��R�[�h�̒�`�N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
final class WEB3QuoteMarketCodes
{

    /**
     * ����
     */
    static final String TOKYO = "1";

    /**
     * ���
     */
    static final String OSAKA = "2";

    /**
     * ����
     */
    static final String NAGOYA = "3";

    /**
     * NNM
     */
    static final String NNM = "5";

    /**
     * ����
     */
    static final String FUKUOKA = "6";

    /**
     * �D��
     */
    static final String SAPPORO = "8";

    /**
     * JASDAQ
     */
    static final String JASDAQ = "9";

    /**
     * �����p�̃}�[�P�b�g�R�[�h���A
     * �Ɩ����W�b�N�p�̃}�[�P�b�g�R�[�h�ɕϊ�����B
     * 
     * @@param marketCode �����p�̃}�[�P�b�g�R�[�h
     */
    static String toWEB3MarketCode(String marketCode)
    {
        // TODO ����萔�Œ�`���Ă��邪�A�{���͒萔�N���X���l���擾
        if (TOKYO.equals(marketCode))
        {
            return "1";
        } else if (OSAKA.equals(marketCode))
        {
            return "2";
        } else if (NAGOYA.equals(marketCode))
        {
            return "3";
        } else if (NNM.equals(marketCode))
        {
            return "9";
        } else if (FUKUOKA.equals(marketCode))
        {
            return "6";
        } else if (SAPPORO.equals(marketCode))
        {
            return "8";
        } else if (JASDAQ.equals(marketCode))
        {
            return "10";
        } else
        {
            throw new IllegalArgumentException();
        }
    }

}
@
