head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊������X�|���X(WEB3FEqConAccountOpenCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import java.util.Date;

/**
 * (�O�������J�݊������X�|���X)<BR>
 * �O�������J�݊������X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_account_open_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;
    
    /**
     * (��t�ԍ�)<BR>
     * ��t�ԍ�
     */
    public String receptionId;
    
    /**
     * (��t����)<BR>
     * ��t����
     */
    public Date receptionDate;
    
    /**
     * @@roseuid 423552AA0222
     */
    public WEB3FEqConAccountOpenCompleteResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3FEqConAccountOpenCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@