head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���ݓ��V�K�������e�N���X(WEB3RuitoNewOrderSpec)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbruito.ordersubmitter.io.RuitoNewOrderSpec;

/**
 * �g���ݓ��V�K�������e�B<BR>
 */
public class WEB3RuitoNewOrderSpec extends RuitoNewOrderSpec
{

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@�R���X�g���N�^���R�[������B<BR>
     * �@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�㗝���͎ҁF ����.�㗝���͎�<BR>
     * �@@�@@is���t�F ����.is���t<BR>
     * �@@�@@�񍆃R�[�h�F �h0�FDEFAULT�h<BR>
     * �@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * �@@�@@�s��R�[�h�F �h0�FDEFAULT�h<BR>
     * �@@�@@�������ʁF ����.��������<BR>
     * �@@�@@�������ʃ^�C�v�F ����.�������ʃ^�C�v<BR>
     * �@@�@@�ŋ敪�F ����.�ŋ敪<BR>
     * @@param l_trader - ����<BR>
     * @@param l_isBuy - ���t�̏ꍇ�� true ���A���̏ꍇ�� false ��ݒ肷��<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_orderQuantity - ��������<BR>
     * @@param l_quantityTypeEnum - �������ʃ^�C�v<BR>
     * @@param l_taxTypeEnum - �ŋ敪<BR>
     * @@roseuid 407C8DA00261
     */
    public WEB3RuitoNewOrderSpec(
        Trader l_trader,
        boolean l_isBuy,
        String l_strProductCode,
        double l_orderQuantity,
        QuantityTypeEnum l_quantityTypeEnum,
        TaxTypeEnum l_taxTypeEnum)
    {
        super(
            l_trader,
            l_isBuy,
            "0",
            l_strProductCode,
            "0",
            l_orderQuantity,
            l_quantityTypeEnum,
            l_taxTypeEnum);
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �P�j�@@super()���R�[������B<BR>
     *  �msuper�ɓn���p�����^�n 
     *     �㗝���͎ҁF ����.�㗝���͎� <BR>
     *     is���t�F ����.is���t <BR>
     *     �񍆃R�[�h�F ����.�񍆃R�[�h <BR>
     *     �����R�[�h�F ����.�����R�[�h <BR>
     *     �s��R�[�h�F ����.�s��R�[�h <BR>
     *     �������ʁF ����.�������� <BR>
     *     �������ʃ^�C�v�F ����.�������ʃ^�C�v <BR>
     *     �ŋ敪�F ����.�ŋ敪 <BR>
     * <BR>
     * @@param l_trader - ����<BR>
     * @@param l_isBuy - ���t�̏ꍇ�� true ���A���̏ꍇ�� false ��ݒ肷��<BR>
     * @@param l_strIssueCode - �񍆃R�[�h<BR>
     * @@param l_strProductCode - �����R�[�h<BR>
     * @@param l_strMarketCode - �s��R�[�h<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@param l_quantityTypeEnum - �������ʃ^�C�v<BR>
     * @@param l_taxTypeEnum - �ŋ敪<BR>
     * @@roseuid 406CE9BE02BE
     */
    public WEB3RuitoNewOrderSpec(
        Trader l_trader,
        boolean l_isBuy,
        String l_strIssueCode,
        String l_strProductCode,
        String l_strMarketCode,
        double l_dblOrderQuantity,
        QuantityTypeEnum l_quantityTypeEnum,
        TaxTypeEnum l_taxTypeEnum)
    {
        //�P�j�@@super()���R�[������B
        super(
            l_trader,
            l_isBuy,
            l_strIssueCode,
            l_strProductCode,
            l_strMarketCode,
            l_dblOrderQuantity,
            l_quantityTypeEnum,
            l_taxTypeEnum);
    }

}
@
