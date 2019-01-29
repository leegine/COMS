head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3NewMiniStockOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����������e(WEB3NewMiniStockOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���]�� (���u) �V�K�쐬
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewMiniStockOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

/**
 * �i�����~�j�����������e�j�B<BR>
 * <BR>
 * �����~�j�����������e�N���X
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3NewMiniStockOrderSpec extends EqTypeNewMiniStockOrderSpec 
{
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@param arg4
     * @@param arg5
     */
    public WEB3NewMiniStockOrderSpec(Trader arg0, boolean arg1, String arg2, String arg3, double arg4, TaxTypeEnum arg5)
    {
        super(arg0, arg1, arg2, arg3, arg4, arg5);
    }

    /**
     * �icreate�����~�j�����������e�j�B<BR>
     * <BR>
     * �istatic���\�b�h�j<BR>
     * �����~�j�����������e�𐶐�����B<BR>
     * <BR>
     * super�N���X�̃R���X�g���N�^���R�[�����A�~�j���������e�𐶐�����B<BR>
     * <BR>
     * [�R���X�g���N�^�̈���]<BR>
     * trader�F�@@����<BR>
     * isBuyOrder�F�@@is������<BR>
     * productCode�F�@@�����R�[�h<BR>
     * marketCode�F�@@�s��R�[�h<BR>
     * quantity�F�@@����<BR>
     * taxType�F�@@TaxTypeEnum.UNDEFINED�i���̑��j<BR>
     * <BR>
     * ���������~�j���������e�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g�i�㗝���͎ҁj
     * 
     * @@param l_blnIsBuyOrder - (is������)<BR>
     * ���������𔻒肷��t���O�B<BR>
     * <BR>
     * true�F�@@������<BR>
     * false�F�@@������<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h
     * @@param l_dblQuantity - (����)<BR>
     * ����
     * @@return WEB3MstkOrderSpec
     */
    public static WEB3NewMiniStockOrderSpec createNewMiniStockOrderSpec(Trader l_trader, boolean l_blnIsBuyOrder, String l_strProductCode, String l_strMarketCode, double l_dblQuantity) 
    {
        WEB3NewMiniStockOrderSpec l_spec = new WEB3NewMiniStockOrderSpec(
            l_trader, l_blnIsBuyOrder, l_strProductCode, l_strMarketCode, l_dblQuantity, TaxTypeEnum.UNDEFINED);
            
        return l_spec;
    }
}
@
