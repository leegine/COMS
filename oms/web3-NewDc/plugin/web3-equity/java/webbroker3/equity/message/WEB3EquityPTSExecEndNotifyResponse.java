head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSExecEndNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�����o���I���ʒm���X�|���X(WEB3EquityPTSExecEndNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 ��іQ(���u) �V�K�쐬 ���f��No.1286
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * ((PTS)�����o���I���ʒm���X�|���X)<BR>
 * (PTS)�����o���I���ʒm���X�|���X�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3EquityPTSExecEndNotifyResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_pts_exec_end_notify";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20080123100000L;

    /**
     * @@roseuid 462CA42601AA
     */
    public WEB3EquityPTSExecEndNotifyResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3EquityPTSExecEndNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
