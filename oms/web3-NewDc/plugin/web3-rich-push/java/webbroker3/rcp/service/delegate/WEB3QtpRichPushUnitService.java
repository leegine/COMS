head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.48.05;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8604d8168182f90;
filename	WEB3QtpRichPushUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : QTP���b�`�N���C�A���g�v�b�V���@@�\�P�ʃT�[�r�X(WEB3QtpRichPushUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/10 ��(FLJ) �V�K�쐬
*/

package webbroker3.rcp.service.delegate;

import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpExcutionInformUnit;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;

/**
 * �iQTP���b�`�N���C�A���g�v�b�V���@@�\�P�ʃT�[�r�X�j�B
 * 
 * @@author ��
 * @@version 1.0
 */
public interface WEB3QtpRichPushUnitService
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
    public WEB3QtpExcutionInformUnit createRichPushXmlMessage(
        Row l_dataRows);
    
    
}
@
