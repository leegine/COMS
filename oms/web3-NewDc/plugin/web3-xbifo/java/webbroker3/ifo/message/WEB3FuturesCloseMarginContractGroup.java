head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginContractGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍό��ʈꗗ���׍s(WEB3FuturesCloseMarginContractGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 羉s (���u) �V�K�쐬
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.640
*/

package webbroker3.ifo.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (�����w���敨�ԍό��ʈꗗ���׍s)<BR>
 * �����w���敨�ԍό��ʈꗗ���׍s�N���X<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginContractGroup extends Message
{

    /**
     * (���P��)<BR>
     */
    public String contractPrice;

    /**
     * (����)<BR>
     */
    public Date openDate;

    /**
     * (�ԍϐ��ʁi�����������ʁj)<BR>
     */
    public String contractOrderQuantity;

    /**
     * (�����P���敪)<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     */
    public String limitPrice;

    /**
     * (��萔��)<BR>
     */
    public String execQuantity;

    /**
     * (���P��)<BR>
     */
    public String execPrice;

    /**
     * (���萔��)<BR>
     */
    public String contractCommission;

    /**
     * (���萔�������)<BR>
     */
    public String contractCommissionConsumptionTax;

    /**
     * (�����)<BR>
     */
    public String contractExecPrice;

    /**
     * (���ϑ��v)<BR>
     */
    public String settleProfitLoss;

    /**
     * (������)<BR>
     */
    public String closeMarginOrderNumber;
    
    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40F7AE1703A9
     */
    public WEB3FuturesCloseMarginContractGroup()
    {

    }
}
@
