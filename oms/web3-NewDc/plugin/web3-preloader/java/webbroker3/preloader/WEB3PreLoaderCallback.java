head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3PreLoaderCallback�N���X(WEB3PreLoaderCallback.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/21 �R�c�@@��i (FLJ) �V�K�쐬
 */
package webbroker3.preloader;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;

/**
 * WEB3DefaultPreLoader�ňꊇ�����ɂ��擾���ꂽ�e���R�[�h�ɑ΂���
 * �������`����R�[���o�b�N�N���X�B
 * 
 * @@author Takuji Yamada (FLJ)
 */
public interface WEB3PreLoaderCallback
{
    
    /**
     * �ꊇ�����Ŏ擾���ꂽ���R�[�h����������B 
     * 
     * @@param l_row �ꊇ�����Ŏ擾���ꂽ���R�[�h
     */    
    public void process(Row l_row) throws DataException;
    
}@
