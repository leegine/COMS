head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorDeleteCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������폜�������X�|���X (WEB3AdminOffFloorDeleteCompleteResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��җ���O���������폜�������X�|���X)<BR>
 * <BR>
 * �Ǘ��җ���O���������폜�T�[�r�X�i�����j�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * -----<English>------------<BR>
 * <BR>
 * WEB3AdminOffFloorDeleteCompleteResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorDeleteService(submit)<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminOffFloorDeleteCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_delete_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (�X�V����)<BR>
     * <BR>
     * �X�V���ԁB<BR>
     * <BR>
     * lastUpdatedTimestamp<BR>
     * <BR>
     */
    public Date lastUpdatedTimestamp;

    /**
     * @@roseuid 421AE406015A
     */
    public WEB3AdminOffFloorDeleteCompleteResponse()
    {

    }

    /**
     * @@roseuid 41FD94160000
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOffFloorDeleteCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
