head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingStateFileDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ڽ��ݽ(WEB3AdminIPOBookBuildingStateFileDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ڽ��ݽ�N���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingStateFileDownloadResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_bookBuildingStateFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408121023L;    
    
    /**
     * �_�E�����[�h�t�@@�C��<BR>
     * <BR>
     * �� CSV�t�@@�C���s�̔z��<BR>
     */
    public String[] downloadFile;
    
    /**
     * ���ݓ���
     */
    public Date currentDate;
    
    /**
     * @@roseuid 4112DAD601A9
     */
    public WEB3AdminIPOBookBuildingStateFileDownloadResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40E11AF30291
     */
    public WEB3AdminIPOBookBuildingStateFileDownloadResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
