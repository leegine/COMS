head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeUnit2.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm����(WEB3AioCashinNoticeUnit2)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/21 ���r (���u) �V�K�쐬
                 : 2006/8/23 �Ԑi(���u) �d�l�ύX ���f�� 614
                 : 2006/11/09 ���G��(���u) �d�l�ύX ���f�� 682
*/
package webbroker3.aio.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����ʒm����)<BR>
 * �����ʒm���׃N���X<BR>
 *
 * @@author ���r(���u)
 * @@version 1.0
 */

public class WEB3AioCashinNoticeUnit2 extends Message
{

    /**
     * (�����ʒm�e�[�u��ID)<BR>
     * �f�[�^�捞�敪 + �����ʒm�e�[�u��ID<BR>
     */
    public String cashinNoticeTableId;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * (�U���˗��l�R�[�h)<BR>
     */
    public String clientCode;

    /**
     * (�U���˗��l��)<BR>
     */
    public String clientName;

    /**
     * (�ڋq��)<BR>
     */
    public String accountName;

    /**
     * (���z)<BR>
     */
    public String price;

    /**
     * (�ʉ݃R�[�h)<BR>
     */
    public String currencyCode;

    /**
     * (�����)<BR>
     */
    public Date settlementDate;

    /**
     * (��s�R�[�h)<BR>
     */
    public String financialInstitutionCode;

    /**
     * (��s��)<BR>
     */
    public String financialInstitutionName;

    /**
     * (�x�X�R�[�h)<BR>
     */
    public String financialBranchCode;

    /**
     * (�x�X��)<BR>
     */
    public String  financialBranchName;
    
    
    /**
     * (�U����s��)<BR>
     */
    public String transferFinancialInstitutionName;

    /**
     * (�U���x�X��)<BR>
     */
    public String  transferFinancialBranchName;    

    /**
     * (���l)<BR>
     */
    public String remark;

    /**
     * (�����敪)<BR>
     */
    public String transactionDiv;

    /**
     * (��������)<BR>
     */
    public Date transactionDate;

    /**
     * (�����敪)<BR>
     */
    public String cashinoutDiv;

    /**
     * (����敪)<BR>
     */
    public String ioTradingType;


    /**
     * (�����ʒm����)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     *
     * @@return webbroker3.aio.message.WEB3AioCashinNoticeUnit2
     * @@roseuid 40E284450344
     */
    public WEB3AioCashinNoticeUnit2()
    {

    }
}
@
