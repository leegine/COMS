head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoCancelOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ޓ��e(WEB3IpoCancelOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���]��(���u) �V�K�쐬
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;


/**
 * ���ޓ��e�N���X<BR>
 * <BR>
 * �� �ȉ��̃��\�b�h�Ɏw�肷������N���X<BR>
 * �@@IPO�\���}�l�[�W��.validate����()<BR>
 * �@@IPO�\���}�l�[�W��submit����()
 * @@author ���]��(���u)
 * @@version 1.0
 */

public class WEB3IpoCancelOrderSpec extends CancelOrderSpec 
{
    
    /**
     * �����
     */
    private Trader trader;
    
    /**
     * (���ޓ��e)<BR>
     * �R���X�g���N�^�B<BR>
     * ���ޓ��e�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X�̃R���X�g���N�^�isuper�j���R�[���A�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * [super�Ɏw�肷�����]<BR>
     * orderId�F�@@IPO�\���h�c<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B�Ɉ����̑㗝���͎҂��Z�b�g����B
     * @@param l_trader - ����ҁi���ҁj�I�u�W�F�N�g
     * 
     * @@param l_lngIpoOrderId - IPO�\���h�c
     * @@return webbroker3.ipo.WEB3IpoCancelOrderSpec
     * @@roseuid 40DBC3D703CA
     */
    public WEB3IpoCancelOrderSpec(Trader l_trader, long l_lngIpoOrderId) 
    {
        super(l_lngIpoOrderId);
        this.trader = l_trader;
    }
    
    /**
     * (get�����)<BR>
     * �igetTrader�j<BR>
     * <BR>
     * this.����҂�ԋp����B
     * @@return Trader
     * @@roseuid 40DBC3D703C9
     */
    public Trader getTrader() 
    {
        return this.trader;
    }
}
@
