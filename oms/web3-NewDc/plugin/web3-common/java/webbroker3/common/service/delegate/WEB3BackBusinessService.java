head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BackBusinessService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ɩ��A�v���P�[�V�����i���菈���j�̋Ɩ����W�b�N�̃C���^�t�F�[�X(WEB3BackBusinessService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.common.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import webbroker3.common.*;
import webbroker3.common.message.*;

/**
 * �Ɩ��A�v���P�[�V�����i���菈���j�̋Ɩ����W�b�N�̃C���^�t�F�[�X�B<BR>
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.boot.Service
 */
public interface WEB3BackBusinessService extends Service
{
    /**
     * �����ŗ^����ꂽ���N�G�X�g����ɋƖ��������s���A�������ʂ����X�|���X�ɐݒ�<BR>
     * ���ĕԂ��B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g
     * @@return �������ʂ��ݒ肳�ꂽ���X�|���X
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
