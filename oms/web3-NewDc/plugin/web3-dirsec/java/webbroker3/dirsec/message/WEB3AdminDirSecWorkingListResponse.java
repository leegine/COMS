head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecWorkingListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�ғ��󋵈ꗗ���X�|���X(WEB3AdminDirSecWorkingListResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/28 �đo�g(���u) �V�K�쐬 ���f��No.117
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�ғ��󋵈ꗗ���X�|���X)<BR>
 * �Ǘ��ҁE�ғ��󋵈ꗗ���X�|���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AdminDirSecWorkingListResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dir_sec_working_list";

    /**
     * serialVersionUID
     */
    public static final long serialVersionUID = 200804281140L;

    /**
     * (�d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�)<BR>
     * �d�q���V�X�e����Е��X�ʃv���t�@@�����X�e�[�u�����R�[�h�ڍ�<BR>
     */
    public WEB3AdminDirSecBatoPreferenceRecordDetail[] batoPreferenceRecord;

    /**
     * @@roseuid 481169CD01DC
     */
    public WEB3AdminDirSecWorkingListResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     */
    public WEB3AdminDirSecWorkingListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
