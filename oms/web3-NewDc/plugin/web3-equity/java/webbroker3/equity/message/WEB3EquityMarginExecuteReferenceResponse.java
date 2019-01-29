head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E�M�p�������Ɖ�X�|���X(WEB3EquityMarginExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 �֔�(���u) �V�K�쐬
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�����E�M�p�������Ɖ�X�|���X)<BR>
 * �����E�M�p�������Ɖ�X�|���X�N���X<BR>
 * @@author  �֔�
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceResponse extends WEB3GenResponse
{

    /**
     * ���O�o�́B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceResponse.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_margin_execute_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701101134L;

    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�ꗗ<BR>
     */
    public String[] marketList;

    /**
     * (�������ꗗ)<BR>
     * �������ꗗ<BR>
     */
    public Date[] orderBizDateList;

    /**
     * (�������ꗗ)<BR>
     * �������ꗗ<BR>
     */
    public WEB3EquityMarginExecuteGroup[] executeGroups;

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
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (ID�ꗗ)<BR>
     * ���������ɊY������S�����h�c<BR>
     * �i�\�[�g���ꂽ��ԁj<BR>
     * <BR>
     * PC�ł̏ꍇ�ݒ�<BR>
     */
    public WEB3EquityMarginOrderIdUnit[] idList;

    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;

    /**
     * �R���X�g���N�^�B<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 45A33C7A03C8
     */
    public WEB3EquityMarginExecuteReferenceResponse(WEB3EquityMarginExecuteReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
