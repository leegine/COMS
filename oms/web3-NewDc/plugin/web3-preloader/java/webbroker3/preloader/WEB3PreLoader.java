head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoader.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3PreLoader�N���X(WEB3PreLoader.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/21 �R�c�@@��i (FLJ) �V�K�쐬
 */
package webbroker3.preloader;

import com.fitechlabs.dbind.RowType;

/**
 * �v�����[�h�������s���N���X����������C���^�[�t�F�[�X
 * 
 * @@author Takuji Yamada (FLJ)
 */
public interface WEB3PreLoader
{
    
    /**
     * �v�����[�h���s���e�[�u����<code>RowType</code>���擾����B
     * 
     * @@return �v�����[�h���s���e�[�u����<code>RowType</code>
     */
    public RowType getRowType();
    
    /**
     * �v�����[�h���s���B
     */
    public void execute();
    
}
@
