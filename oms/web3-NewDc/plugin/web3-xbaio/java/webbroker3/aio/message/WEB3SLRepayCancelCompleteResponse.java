head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍώ���������X�|���X(WEB3SLRepayCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.758
*/

package webbroker3.aio.message;

import java.util.Date;

/**
 * (�،��S�ۃ��[���ԍώ���������X�|���X)<BR>
 * �S�ۃ��[���ԍώ���������X�|���X�N���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3SLRepayCancelCompleteResponse extends WEB3SLRepayCancelCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_cancel_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121513L;

    /**
     * (�X�V����)<BR>
     * DB�̍X�V����<BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * @@roseuid 46E890860173
     */
    public WEB3SLRepayCancelCompleteResponse(WEB3SLRepayCancelCompleteRequest l_request)
    {
        super(l_request);
    }
}
@
