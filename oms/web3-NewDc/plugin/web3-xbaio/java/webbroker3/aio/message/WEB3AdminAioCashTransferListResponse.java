head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashTransferListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���ꗗ���ʃ��X�|���X(WEB3AdminAioCashTransferListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 �����q (���u) �V�K�쐬�@@�d�l�ύX���f�� NO.693�ANO.695
Revision History : 2007/02/16 �������I (SCS) �d�l�ύX�E�����̖�� NO.006
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (���o���ꗗ���ʃ��X�|���X)<BR>
 * ���o���ꗗ���ʃ��X�|���X�N���X<BR>
 *
 * @@author �����q (���u)
 * @@version 1.0
 */
public class WEB3AdminAioCashTransferListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cash_transfer_list";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200702051000L;

    /**
     * (SONAR�������v���z)<BR>
     * SONAR�������v���z<BR>
     */
    public String sonarCashinTotal = "0";

    /**
     * (�o�[�`�����������v���z)<BR>
     * �o�[�`�����������v���z<BR>
     */
    public String virtualCashinTotal = "0";

    /**
     * (�l�b�g�������v���z)<BR>
     * �l�b�g�������v���z<BR>
     */
    public String netCashinTotal = "0";

    /**
     * (�o�����v���z)<BR>
     * �o�����v���z<BR>
     */
    public String cashoutTotal = "0";

    /**
     * (�U�ցi�a������犔��؋����j���v���z)<BR>
     * �U�ցi�a������犔��؋����j���v���z<BR>
     */
    public String transferTotalDepositToMargin = "0";

    /**
     * (�U�ցi����؋�������a����j���v���z)<BR>
     * �U�ցi����؋�������a����j���v���z<BR>
     */
    public String transferTotalMarginToDeposit = "0";

    /**
     * (�ב֕ۏ؋��U�ցi�a�������ב֕ۏ؋��j���v���z)<BR>
     * �ב֕ۏ؋��U�ցi�a�������ב֕ۏ؋��j���v���z<BR>
     */
    public String fxTotalDepositToGuaranty = "0";

    /**
     * (�ב֕ۏ؋��U�ցi�ב֕ۏ؋�����a����j���v���z)<BR>
     * �ב֕ۏ؋��U�ցi�ב֕ۏ؋�����a����j���v���z<BR>
     */
    public String fxTotalGuarantyToDeposit = "0";

    /**
     * (���̑��U�ցi�a�������X�j���v���z)<BR>
     * ���̑��U�ցi�a�������X�j���v���z<BR>
     */
    public String otherTotalAccountBalanceToX = "0";

    /**
     * (���̑��U�ցiX����a����j���v���z)<BR>
     * ���̑��U�ցiX����a����j���v���z<BR>
     */
    public String otherTotalXToAccountBalance = "0";

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;

    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;

    /**
     * (���o���ꗗ���׈ꗗ)<BR>
     * ���o���ꗗ���׈ꗗ<BR>
     */
    public WEB3AioAdminCashTransferListUnit[] cashTransferListUnits;

    /**
     * @@roseuid 45C3F157035B
     */
    public WEB3AdminAioCashTransferListResponse() 
    {

    }
    
    /**
     * @@roseuid 4158EB66008E
     */
    public WEB3AdminAioCashTransferListResponse(WEB3AdminAioCashTransferListRequest l_request) 
    {
        super(l_request);
    }
}
@
