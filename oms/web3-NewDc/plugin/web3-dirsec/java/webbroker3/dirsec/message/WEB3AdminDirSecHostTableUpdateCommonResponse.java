head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecHostTableUpdateCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�L���[�e�[�u���X�V���ʃ��X�|���X(WEB3AdminDirSecHostTableUpdateCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30 ����(���u) �V�K�쐬
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҁE�L���[�e�[�u���X�V���ʃ��X�|���X)<BR>
 * �Ǘ��ҁE�L���[�e�[�u���X�V���ʃ��X�|���X�N���X�B<BR>
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminDirSecHostTableUpdateCommonResponse extends WEB3GenResponse 
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603291625L;
    /**
     * (�e�[�u����)<BR>
     * �e�[�u�����i�a���j�B
     */
    public String tableJpnName;
    
    /**
     * (�e�[�u��������)<BR>
     * �e�[�u���������B
     */
    public String tableName;
    
    /**
     * (���ʃR�[�h�L���t���O)<BR>
     * ���ʃR�[�h�L���t���O�B<BR>
     * <BR>
     * 0�F�Ȃ�<BR>
     * 1�F�L��<BR>
     */
    public String identityCodeFlag;
    
    /**
     * (�쐬���t�L���t���O)<BR>
     * �쐬���t�L���t���O�B<BR>
     * <BR>
     * 0�F�Ȃ�<BR>
     * 1�F�L��<BR>
     */
    public String createDateFlag;
    
    /**
     * @@roseuid 442A1C8901A5
     */
    public WEB3AdminDirSecHostTableUpdateCommonResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminDirSecHostTableUpdateCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
