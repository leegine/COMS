head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�f�[�^�v�b�V���@@�\�P�ʃT�[�r�X���ʃC���^�[�t�F�[�X(WEB3RichPushUnitService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;

/**
 * �i���b�`�N���C�A���g�v�b�V���@@�\�P�ʃT�[�r�X�j�B
 * @@author ��
 * @@version 1.0
 */
public interface WEB3RichPushUnitService
    extends Service
{
    /**
     * ���b�`�N���C�A���g�f�[�^�v�b�V���@@�\�P�ʃT�[�r�X���ʃC���^�[�t�F�[�X
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     */
    public void push(long l_lngFromAccountId, long l_lngToAccountId) throws
        DataQueryException, DataNetworkException;

    /**
     * ���b�`�N���C�A���g�v�b�V���f�[�^��XML�֕ϊ�
     *
     * @@param l_dataRows List
     * @@return String
     */
    public String createRichPushXmlMessage(
        List l_dataRows);
}
@
