head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3PreLoaderManager�N���X(WEB3PreLoaderManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/07 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.preloader;

import com.fitechlabs.xtrade.kernel.boot.Service;


/**
 * �v�����[�_�[���Ǘ�����N���X
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public interface WEB3PreLoaderManager extends Service
{
    
    /**
     * �V�����v�����[�_�[��o�^����B
     * 
     * @@param l_preLoader �o�^����v�����[�_�[
     */
    public void registerPreLoader(WEB3PreLoader l_preLoader);
    
    /**
     * �w�肵���e�[�u�����Ɋ֘A�t�����Ă���v�����[�_�[�̔z����擾����B
     * �w�肵���e�[�u�����Ɋ֘A�t�����Ă���v�����[�_�[���o�^����Ă��Ȃ��ꍇ�́A
     * <code>null</code>��Ԃ��B
     * 
     * @@param l_strTableName �e�[�u����
     * @@return WEB3PreLoader�̔z��B
     */
    public WEB3PreLoader[] getPreLoader(String l_strTableName);
    
    /**
     * �w�肵���e�[�u�����Ɋ֘A�t�����Ă���v�����[�_�[�����s����B
     * 
     * @@param l_strTableName �e�[�u����
     */
    public void load(String l_strTableName);
    
    /**
     * �w�肵���e�[�u���Ɋ֘A�t�����Ă���v�����[�_�[�����s����B
     * 
     * @@param l_strTableNames �e�[�u�����̔z��
     * @@param l_intThreadsSize �v�����[�_�[�����s����X���b�h�̐�
     */
    public void load(String[] l_strTableNames, int l_intThreadsSize);
    
    /**
     * �o�^����Ă��邷�ׂẴv�����[�_�[�����s����B
     */
    public void loadAll();

}
@
