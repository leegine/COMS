head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s��R�[�h�@@�萔��`�C���^�t�F�C�X(WEB3MarketCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 ���� ���D(SRA) �V�K�쐬
                   2004/06/10 �n�U�c (���u) �C��
                   2006/12/25 �h�C(���u) �d�l�ύX�E���f��No219
                   2007/12/18 �ЋŃV��(���u) �c�a���C�A�E�g(�s��e�[�u��)�ɂ��
*/
package webbroker3.common.define;

/**
 * �s��R�[�h�@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author ���� ���D(SRA)
 * @@version 1.0
 */
public interface WEB3MarketCodeDef
{

    /**
     * �f�t�H���g
     */
    public static final String DEFAULT = "0";

    /**
     * ����
     */
    public static final String TOKYO = "1";
    
    /**
     * ���
     */
    public static final String OSAKA = "2";

    /**
     * ���É�
     */    
    public static final String NAGOYA = "3";
    
    /**
     * ���s
     */
    //public static final String KYOTO = "4";
    
    /**
     * �L��
     */
    //public static final String HIROSHIMA = "5";
    
    /**
     * ����
     */
    public static final String FUKUOKA = "6";
    
    /**
     * �D�y
     */
    public static final String SAPPORO = "8";
    
    /**
     * NNM
     */
    public static final String NNM = "9";
    
    /**
     * JASDAQ
     */
    public static final String JASDAQ = "10";
    
    /**
     * ���`
     */
    public static final String HONGKONG = "N1";

    /**
     * �[�Z��
     */
    public static final String SHENZHEN = "N2";

    /**
     * ��C
     */
    public static final String SHANGHAI = "X1";

    /**
     * �D��s��
     */
    public static final String PRIORITY_MARKET = "99";
    
    /**
     * JNX-PTS
     */
    public static final String JNX_PTS = "11";
}

@
