head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DigestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3DigestService�N���X(WEB3DigestService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/6/07 ��(FLJ) �V�K�쐬
*/
package webbroker3.login.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * WEB3�ɕK�v�ȃZ�L�����e�B�[�L�[�𐶐�����ƔF�؂���T�[�r�X�̃C���^�t�F�[�X�B
 */
public interface WEB3DigestService extends Service
{
    /**
     * Web3�ɕK�v�ȃL�[���쐬�A�擾
     * 
     * @@return �L�[
     */
    public WEB3DigestKey getRandomKey();
    
    /**
     * �L���ȃL�[�ł��邩���`���b�N����B
     * 
     * @@param key �`���b�N�����L�[�I�u�W�F�N�g
     * @@return �L���ł��邩�ǂ����B�L���̏ꍇ:true,������:false
     */
    public boolean isValidKey(WEB3DigestKey l_key);
    
}
@
