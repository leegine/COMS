head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.47.52;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8604d8168182f90;
filename	WEB3RichPushGateWayService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�v�b�V���Q�[�g�E�F�C�T�[�r�X�C���^�[�t�F�[�X(WEB3RichPushGateWayService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp.service.delegate;

import java.util.*;

import com.fitechlabs.xtrade.kernel.boot.*;

/**
 * ���b�`�N���C�A���g�v�b�V���Q�[�g�E�F�C�T�[�r�X�C���^�[�t�F�[�X
 * @@author ��
 * @@version 1.0
 */
public interface WEB3RichPushGateWayService
    extends Service
{

    /**
     * �Q�[�g�E�F�C�o�R���b�`�N���C�A���g�փf�[�^�v�b�V��
     *
     * @@param l_strInstitutionCode String
     * @@param l_lstPushData List
     * @@return boolean
     */
    public boolean push(String l_strInstitutionCode, List l_lstPushData);

}
@