head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇���_�E�����[�h���X�|���X(WEB3AdminAioCashoutInqDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/31 ����� (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o���\���⍇���_�E�����[�h���X�|���X)<BR>
 * �o���\���⍇���_�E�����[�h���X�|���X�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3AdminAioCashoutInqDownloadResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_download";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200607311248L;   
    
    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C��<BR>
     * <BR>
     * �� CSV�t�@@�C���s�̔z�� <BR>
     */
    public String[] downloadFile;
    
    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;
    
    /**
     * @@roseuid 4159EB6602DD
     */
    public WEB3AdminAioCashoutInqDownloadResponse(WEB3AdminAioCashoutInqDownloadRequest l_request) 
    {
        super(l_request);
    }
}
@
