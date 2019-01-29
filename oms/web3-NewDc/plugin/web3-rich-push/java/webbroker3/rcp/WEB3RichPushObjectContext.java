head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.24.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushObjectContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�v�b�V���I�u�W�F�N�g�\�[�g�p�R���e�N�X�g�N���X(WEB3RichPushObjectContext.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/01 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp;

import java.util.*;

/**
 * <p>
 * ���b�`�N���C�A���g�v�b�V���I�u�W�F�N�g�\�[�g�p�R���e�N�X�g�N���X�B<br>
 * <br>
 * </p>
 *
 * @@author FLJ��
 * @@version 1.0
 */

public class WEB3RichPushObjectContext
{
    /**
     * �v�b�V���I�u�W�F�N�g�ꗗ
     */
    private List pushObjects = new ArrayList();

    /**
     * �v�b�V���I�u�W�F�N�g�ƃv�b�V���I�����I�u�W�F�N�̃g�}�b�s���O
     */
    private Map pushHistoryObjects = new Hashtable();

    /**
     * �v�b�V���I�u�W�F�N�g�ƃv�b�V���I�����I�u�W�F�N�̃g�}�b�s���O��ݒ肷��
     *
     * @@param l_mapPushHistoryRecords Map
     */
    public void setPushHistoryObjects(Map l_mapPushHistoryObjetcs)
    {
        this.pushHistoryObjects = l_mapPushHistoryObjetcs;
    }

    /**
     * �v�b�V���I�u�W�F�N�g�ꗗ��ݒ肷��
     *
     * @@param l_lstPushObjects List
     */
    public void setPushObjects(List l_lstPushObjects)
    {
        this.pushObjects = l_lstPushObjects;
    }

    /**
     * �v�b�V���I�u�W�F�N�g�ꗗ���擾����
     *
     * @@return List
     */
    public List getPushObjects()
    {
        return pushObjects;
    }

    /**
     * �v�b�V���I�u�W�F�N�g�ƃv�b�V���I�����I�u�W�F�N�̃g�}�b�s���O���擾����
     *
     * @@return Map
     */
    public Map getPushHistoryObjects()
    {
        return pushHistoryObjects;
    }

}@
