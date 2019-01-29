head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCancelOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������������e(WEB3EquityCancelOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 ���_�� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/11/01 ��іQ (���u) ���f�� No.1046
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

/**
 * �i��������������e�j�B<BR>
 * <BR>
 * ����������e�̓��͂�\������B<BR>
 * �����}�l�[�W���ɓn���p�����^�B<BR>
 * <BR>
 * xTrade��EqTypeCancelOrderSpec���g�������N���X�B
 * @@version 1.0
 */
public class WEB3EquityCancelOrderSpec extends EqTypeCancelOrderSpec
{

    /**
     * (����)<BR>
     * �㗝���͈��ҁB<BR>
     */
    private Trader trader;

    /**
     * (��������������e)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X����<BR>
     * �@@�X�[�p�[�N���X�̃R���X�g���N�^�iEqTypeCancelOrderSpec�j���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@orderId�F�@@�i�������ҏW�j<BR>
     * <BR>
     * �Q�j�@@�g���v���p�e�B���Z�b�g����<BR>
     * <BR>
     * �@@�|���ҁF�@@�i�������ҏW�j<BR>
     * <BR>
     * @@param l_orderID - (�����h�c)<BR>
     * �����h�c<BR>
     * @@param l_trader - (����)<BR>
     * ����<BR>
     * @@return WEB3EquityOrderCancelSpec<BR>
     * @@roseuid 4031B81301B5<BR>
     */
    public WEB3EquityCancelOrderSpec(long l_orderID, Trader l_trader)
    {
        //�C���X�^���X����
        super(l_orderID);

        //�g���v���p�e�B���Z�b�g����
        this.trader = l_trader;
    }

    /**
     * (get����)<BR>
     * ���҂��擾����B<BR>
     * @@return Trader<BR>
     * @@roseuid 4031B7F10261<BR>
     */
    public Trader getTrader()
    {
        return this.trader;
    }

}
@
