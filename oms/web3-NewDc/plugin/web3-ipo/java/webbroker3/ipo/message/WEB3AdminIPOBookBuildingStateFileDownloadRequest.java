head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOBookBuildingStateFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ظ���(WEB3AdminIPOBookBuildingStateFileDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �ė� (���u) �V�K�쐬
Revision History : 2005/08/19 ��c(SRA) ���捞�Č�IPO-No.76�i�p�t�H�[�}���X���P�j
                 : 2006/11/09 ꎉ� (���u) �d�l�ύX�E���f��160                
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ظ��ăN���X
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIPOBookBuildingStateFileDownloadRequest extends WEB3GenRequest 
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
     * IPO�����h�c
     */
    public String id;
    
    /**
     * �t�@@�C�����<BR>
     * <BR>
     * 0�F�@@����OP�f�[�^���_�E�����[�h����B<BR>
     * 1�F�@@BB�󋵃f�[�^�i�]�͂���j���_�E�����[�h����B<BR>
     * 2�F�@@BB�󋵃f�[�^�i�]�͂Ȃ��j���_�E�����[�h����B<BR>
     */
    public String fileTypeCode;
    
    /**
     * (���X�R�[�h) <BR>
     * ��ʂɂđI�����ꂽ���X�R�[�h <BR>
     * �������͂̏ꍇ�́APR�w�ŕێ����Ă��� <BR>
     * �Ǘ��Ҏ戵�\���X�̈ꗗ���Z�b�g�B
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�hfrom) <BR>
     * ��ʂɂē��͂��ꂽ�ڋq�R�[�hfrom <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public String accountCodeFrom;

    /**
     * (�ڋq�R�[�hto) <BR>
     * ��ʂɂē��͂��ꂽ�ڋq�R�[�hto <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public String accountCodeTo;

    /**
     * (�V�K�\������from) <BR>
     * ��ʂɂē��͂��ꂽ�V�K�\������from <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public Date bbCreatedTimestampFrom;

    /**
     * (�V�K�\������to) <BR>
     * ��ʂɂē��͂��ꂽ�V�K�\������to <BR>
     * <BR>
     * ��null�F�w��Ȃ�
     */
    public Date bbCreatedTimestampTo;
    
    /**
     * (CSV�敪 = 0) <BR>
     * CSV�敪 <BR>
     * <BR>
     * 0�F�ǉ����ږ�<BR>
     * 1�F�ǉ����ڗL�i���҃R�[�h�j<BR>
     */
    public String csvDiv = "0";

    /**
     * @@roseuid 4112DAD60221
     */
    public WEB3AdminIPOBookBuildingStateFileDownloadRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DAD60235
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOBookBuildingStateFileDownloadResponse(this);
    }
}
@
