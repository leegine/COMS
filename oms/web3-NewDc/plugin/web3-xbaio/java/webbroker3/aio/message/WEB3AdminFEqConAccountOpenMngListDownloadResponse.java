head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngListDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��ꗗ�_�E�����[�h���X�|���X(WEB3AdminFEqConAccountOpenMngListDownloadResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import java.util.Date;

/**
 * (�O�������J�݊Ǘ��ꗗ�_�E�����[�h���X�|���X)<BR>
 * �O�������J�݊Ǘ��ꗗ�_�E�����[�h���X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngListDownloadResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_list_download";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171813L;
    
    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C��
     */
    public String[] downloadFile;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��
     */
    public String totalRecords;
    
    /**
     * (���ݓ���)<BR>
     * ���ݓ���
     */
    public Date currentDate;
    
    /**
     * @@roseuid 423552EB03C8
     */
    public WEB3AdminFEqConAccountOpenMngListDownloadResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFEqConAccountOpenMngListDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
