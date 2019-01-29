head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketCodeSONARDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s��R�[�h�i�r�n�m�`�q�j�@@�萔��`�N���X(WEB3MarketCodeSONARDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 ���� ���D(SRA) �V�K�쐬
Revesion History : 2004/11/26 羐� (���u) �C��
Revesion History : 2007/12/18 �ЋŃV�� (���u) �y���ʁz�d�l�ύX�Ǘ��䒠No568(�c�a���C�A�E�g)��Ή�
Revesion History : 2009/09/02 ��іQ (���u) �y���ʁz�d�l�ύX�Ǘ��䒠 �c�a���C�A�E�gNo.694
*/
package webbroker3.common.define;

/**
 * �s��R�[�h�i�r�n�m�`�q�j�@@�萔��`�N���X
 *
 * @@author ���� ���D(SRA)
 * @@version 1.0
 */
public class WEB3MarketCodeSONARDef
{

    /**
     * 1�F����
     */
    public static final String TOKYO = "1";
    
    /**
     *  2�F���
     */
    public static final String OSAKA = "2";

    /**
     * 3�F���É�
     */    
    public static final String NAGOYA = "3";
    
    /**
     * 4�F�D�y
     */
    public static final String SAPPORO = "4";
    
    /**
     * 5�FNNM
     */
    public static final String NNM = "5";
    
    /**
     * 6�FJASDAQ
     */
    public static final String JASDAQ = "6";
    
    /**
     * 9�F����
     */
    public static final String FUKUOKA = "9";
    
    /**
     * P:JNX-PTS
     */
    public static final String JNX_PTS = "P";

    /**
     * x:���`
     */
    public static final String HONG_KONG = "x";

    /**
     * y:��C
     */
    public static final String SHANG_HAI = "y";

    /**
     * z:�[�Z��
     */
    public static final String SHEN_ZHEN = "z";

    /**
     * �w�肵���s��R�[�h�ɊY������s��R�[�h�iSONAR�j��ԋp����B<BR>
     * �Y���s��R�[�h�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * @@param l_marketCode�@@�s��R�[�h
     * @@return
     */
    public static String getMarketCodeSONAR(String l_marketCode) {
        
        if (l_marketCode.equals(WEB3MarketCodeDef.TOKYO)) {
            return WEB3MarketCodeSONARDef.TOKYO;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.OSAKA)) {
            return WEB3MarketCodeSONARDef.OSAKA;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.NAGOYA)) {
            return WEB3MarketCodeSONARDef.NAGOYA;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.NNM)) {
            return WEB3MarketCodeSONARDef.NNM;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.FUKUOKA)) {
            return WEB3MarketCodeSONARDef.FUKUOKA;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.SAPPORO)) {
            return WEB3MarketCodeSONARDef.SAPPORO;
        }
        else if (l_marketCode.equals(WEB3MarketCodeDef.JASDAQ)) {
            return WEB3MarketCodeSONARDef.JASDAQ;
        }        
        return null;
        
    }
    
    /**
     * �w�肵���s��R�[�h�iSOANR�j�ɊY������s��R�[�h��ԋp����B<BR>
     * �Y���s��R�[�h�iSONAR�j�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * @@param l_marketCodeSONAR�@@�s��R�[�h�iSONAR�j
     * @@param l_listType ���敪�FWEB3ListTypeDef�ɒ�`�����l
     * @@return
     */
    public static String getMarketCode(String l_marketCodeSONAR,
        String l_listType) {

        if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.TOKYO)) {
            if (l_listType.equals(WEB3ListTypeDef.OTC)) 
            {
                return WEB3MarketCodeDef.JASDAQ;
            }
            else
            {
                return WEB3MarketCodeDef.TOKYO;
            }
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.OSAKA)) {
            return WEB3MarketCodeDef.OSAKA;
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.NAGOYA)) {
            return WEB3MarketCodeDef.NAGOYA;
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.NNM)) {
            return WEB3MarketCodeDef.NNM;
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.FUKUOKA)) {
            return WEB3MarketCodeDef.FUKUOKA;
        }
        else if (l_marketCodeSONAR.equals(WEB3MarketCodeSONARDef.SAPPORO)) {
            return WEB3MarketCodeDef.SAPPORO;
        }
        return null;
        
    }
    
}
@
